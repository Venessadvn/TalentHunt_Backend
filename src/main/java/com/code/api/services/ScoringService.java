package com.code.api.services;

import com.code.api.models.JudgeScore;
import com.code.api.reposatories.JudgeScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    @Autowired
    private JudgeScoreRepository judgeScoreRepository;

    public JudgeScore scoreContestant(JudgeScore judgeScore) {
        try {
            System.out.println("=== ScoringService: Saving score ===");
            System.out.println("Contestant: " + judgeScore.getContestant().getName());
            System.out.println("Judge: " + judgeScore.getJudge().getName());
            System.out.println("Score: " + judgeScore.getScore());
            
            JudgeScore saved = judgeScoreRepository.save(judgeScore);
            System.out.println("Score saved with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            System.err.println("Error in ScoringService.scoreContestant: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
