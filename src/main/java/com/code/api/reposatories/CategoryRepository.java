package com.code.api.reposatories;

import com.code.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    // Find category by name (using Optional)
    Optional<Category> findByCatname(String catname);
    
    // Check if category exists by name
    boolean existsByCatname(String catname);
}
