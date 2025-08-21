package com.code.api.controllers;

import com.code.api.models.Contestant;
import com.code.api.models.Judge;
import com.code.api.models.JudgeScore;
import com.code.api.services.ScoringService;
import com.code.api.reposatories.ContestantRepository;
import com.code.api.reposatories.JudgeRepository;
import com.code.api.reposatories.JudgeScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoringController {

    @Autowired
    private ScoringService scoringService;

    @Autowired
    private ContestantRepository contestantRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private JudgeScoreRepository judgeScoreRepository;

    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody ScoreRequest request) {
        try {
            // Log received data
            System.out.println("=== Score Submission ===");
            System.out.printf("Contestant ID: %d, Judge ID: %d, Score: %.2f, Category: %s%n",
                    request.getContestantId(), request.getJudgeId(), request.getScore(), request.getCategory());

            // Input validation
            if (request.getContestantId() <= 0 || request.getJudgeId() <= 0) {
                return ResponseEntity.badRequest().body("Invalid Judge or Contestant ID.");
            }

            if (request.getScore() < 0 || request.getScore() > 10) {
                return ResponseEntity.badRequest().body("Score must be between 0 and 10.");
            }

            // Fetch entities
            Optional<Contestant> contestantOpt = contestantRepository.findById(request.getContestantId());
            if (contestantOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contestant not found.");
            }
            Contestant contestant = contestantOpt.get();

            Optional<Judge> judgeOpt = judgeRepository.findById(request.getJudgeId());
            if (judgeOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Judge not found.");
            }
            Judge judge = judgeOpt.get();

            // Check if score already exists
            Optional<JudgeScore> existingScore = judgeScoreRepository.findByJudgeAndContestant(judge, contestant);
            if (existingScore.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("This judge has already scored this contestant.");
            }

            // Create and save score
            JudgeScore judgeScore = new JudgeScore();
            judgeScore.setContestant(contestant);
            judgeScore.setJudge(judge);
            judgeScore.setScore(request.getScore());

            JudgeScore savedScore = scoringService.scoreContestant(judgeScore);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ScoreResponse(
                    "Score added successfully",
                    savedScore.getId(),
                    contestant.getName(),
                    judge.getName(),
                    savedScore.getScore()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }

    // ================= DTO CLASSES =================

    public static class ScoreRequest {
        private int contestantId;
        private int judgeId;
        private double score;
        private String category;

        public ScoreRequest() {}

        public int getContestantId() { return contestantId; }
        public void setContestantId(int contestantId) { this.contestantId = contestantId; }

        public int getJudgeId() { return judgeId; }
        public void setJudgeId(int judgeId) { this.judgeId = judgeId; }

        public double getScore() { return score; }
        public void setScore(double score) { this.score = score; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
    }

    public static class ScoreResponse {
        private String message;
        private Integer scoreId;
        private String contestantName;
        private String judgeName;
        private double score;

        public ScoreResponse(String message, Integer scoreId, String contestantName, String judgeName, double score) {
            this.message = message;
            this.scoreId = scoreId;
            this.contestantName = contestantName;
            this.judgeName = judgeName;
            this.score = score;
        }

        public String getMessage() { return message; }
        public Integer getScoreId() { return scoreId; }
        public String getContestantName() { return contestantName; }
        public String getJudgeName() { return judgeName; }
        public double getScore() { return score; }
    }
}
