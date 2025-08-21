package com.code.api.reposatories;

import com.code.api.models.Judge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JudgeRepository extends JpaRepository<Judge, Integer> {
    Optional<Judge> findByEmailAndPassword(String email, String password);
    Optional<Judge> findByEmail(String email); // 🔥 Add this
}
