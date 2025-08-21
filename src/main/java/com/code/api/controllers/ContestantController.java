package com.code.api.controllers;

import com.code.api.models.Contestant;
import com.code.api.models.Category;
import com.code.api.reposatories.ContestantRepository;
import com.code.api.reposatories.CategoryRepository;
import com.code.api.dto.ContestantRegistrationDTO;
import com.code.api.dto.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contestants")
@CrossOrigin(origins = "*")
public class ContestantController {

    @Autowired
    private ContestantRepository contestantRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // 🔹 Register new contestant
    @PostMapping("/register")
    public ResponseEntity<?> registerContestant(@RequestBody ContestantRegistrationDTO dto) {
        if (contestantRepo.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already registered"));
        }

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Invalid category ID"));

        Contestant contestant = new Contestant(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                category
        );

        contestantRepo.save(contestant);

        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }

    // 🔹 Login contestant
    @PostMapping("/login")
    public ResponseEntity<?> loginContestant(@RequestBody LoginDTO dto) {
        Contestant contestant = contestantRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (contestant == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        return ResponseEntity.ok(Map.of(
                "userid", contestant.getContestantId(),
                "name", contestant.getName(),
                "email", contestant.getEmail(),
                "role", "contestant"
        ));
    }

    // 🔹 Get all contestants
    @GetMapping
    public List<Contestant> getAllContestants() {
        return contestantRepo.findAll();
    }

    // 🔹 Get contestant by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getContestantById(@PathVariable int id) {
        return contestantRepo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Contestant not found")));
    }
}
