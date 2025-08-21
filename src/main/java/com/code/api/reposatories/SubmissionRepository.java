package com.code.api.reposatories;

import com.code.api.models.Submission;
import com.code.api.models.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    // Find all submissions by a given contestant
    List<Submission> findByContestant(Contestant contestant);

    // Optional: find all submissions ordered by date desc (latest first)
    List<Submission> findByContestantOrderBySubmissionDateDesc(Contestant contestant);
}
