package com.code.api.reposatories;

import com.code.api.models.Category;
import com.code.api.models.Contestant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestantRepository extends JpaRepository<Contestant, Integer> {

    // Find all contestants by category
    List<Contestant> findByCategory(Category category);

    boolean existsByEmail(String email);

    boolean existsByNameAndCategory_Catname(String name, String catname);

    Contestant findByEmailAndPassword(String email, String password);

    // Find top N contestants ordered by totalScore descending
    List<Contestant> findAllByOrderByTotalScoreDesc(Pageable pageable);

    // If you have removed User, remove any user-related methods here
}
