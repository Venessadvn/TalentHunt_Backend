package com.code.api.controllers;

import com.code.api.models.Category;
import com.code.api.models.Contestant;
import com.code.api.services.CategoryService;
import com.code.api.services.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContestantService contestantService;

    // GET all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Register contestant for a category WITHOUT users
    @PostMapping("/register")
    public ResponseEntity<?> registerContestant(@RequestBody Map<String, Object> request) {
        try {
            String categoryName = (String) request.get("category");
            Map<String, Object> contestantData = (Map<String, Object>) request.get("contestant");

            if (categoryName == null || contestantData == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Category and contestant information are required"));
            }

            Category category = categoryService.getCategoryByName(categoryName);
            if (category == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Category '" + categoryName + "' does not exist"));
            }

            // You can include whatever fields you want for contestant like name, email etc
            String contestantName = (String) contestantData.get("name");
            if (contestantName == null || contestantName.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Contestant name is required"));
            }

            // Check if contestant already registered in this category (if you want)
            boolean alreadyRegistered = contestantService.isContestantRegistered(contestantName, categoryName);
            if (alreadyRegistered) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Contestant is already registered for this category"));
            }

            // Create contestant entity and save
            Contestant contestant = new Contestant();
            contestant.setName(contestantName);
            contestant.setCategory(category);

            Contestant savedContestant = contestantService.saveContestant(contestant);

            return ResponseEntity.ok(Map.of(
                    "message", "Contestant registered successfully",
                    "contestantId", savedContestant.getContestantId(),
                    "category", categoryName,
                    "contestantName", contestantName
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error: " + e.getMessage()));
        }
    }

    // POST - Create new category (admin function)
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category existingCategory = categoryService.getCategoryByName(category.getCatname());
            if (existingCategory != null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Category already exists"));
            }

            Category savedCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error: " + e.getMessage()));
        }
    }

    // PUT - Update category
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        category.setCategoryId(id);
        Category updatedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(id);
        return ResponseEntity.ok(Map.of("message", "Category deleted successfully"));
    }
}
