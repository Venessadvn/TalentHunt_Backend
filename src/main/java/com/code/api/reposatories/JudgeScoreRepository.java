package com.code.api.reposatories;

import com.code.api.models.Contestant;
import com.code.api.models.Judge;
import com.code.api.models.JudgeScore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeScoreRepository extends JpaRepository<JudgeScore, Integer> {
	 Optional<JudgeScore> findByJudgeAndContestant(Judge judge, Contestant contestant);
    // You can add custom queries here if needed
}
