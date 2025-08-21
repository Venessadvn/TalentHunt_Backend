package com.code.api.reposatories;

import com.code.api.models.Contestant;
import com.code.api.models.VideoSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoSubmissionRepository extends JpaRepository<VideoSubmission, Integer> {

    // 🔹 Find videos by contestantId (int)
    List<VideoSubmission> findByContestant_ContestantId(int contestantId);

    // 🔹 Find videos by Contestant entity
    List<VideoSubmission> findByContestant(Contestant contestant);

    // 🔹 Top videos by total judge scores (sum of scores per contestant)
    @Query("""
        SELECT v FROM VideoSubmission v
        JOIN v.contestant c
        LEFT JOIN c.judgeScores js
        GROUP BY v
        ORDER BY COALESCE(SUM(js.score), 0) DESC
    """)
    List<VideoSubmission> findTopVideosByScore(Pageable pageable);
}
