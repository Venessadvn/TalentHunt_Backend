package com.code.api.services;

import com.code.api.models.Category;
import com.code.api.reposatories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get category by ID
    public Category getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    // Get category by name - returns null if not found
    public Category getCategoryByName(String name) {
        Optional<Category> category = categoryRepository.findByCatname(name);
        return category.orElse(null);
    }

    // Save category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Delete category
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    // Check if category exists by name
    public boolean categoryExists(String name) {
        return categoryRepository.existsByCatname(name);
    }
}
