package com.code.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "judgescores")
public class JudgeScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private int scoreId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contestant_id", nullable = false)
    @JsonBackReference 
    private Contestant contestant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "judge_id", nullable = false)
    private Judge judge;

    @Column(name = "score", nullable = false)
    private double score;

    // --- Constructors ---

    public JudgeScore() {
        // Default constructor
    }

    public JudgeScore(Contestant contestant, Judge judge, double score) {
        this.contestant = contestant;
        this.judge = judge;
        this.score = score;
    }

    // --- Getters and Setters ---

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getId() {
        return scoreId; // Compatibility method
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    // --- Optional: toString() for debugging ---

    @Override
    public String toString() {
        return "JudgeScore{" +
                "scoreId=" + scoreId +
                ", score=" + score +
                ", contestantId=" + (contestant != null ? contestant.getContestantId() : "null") +
                ", judgeId=" + (judge != null ? judge.getJudgeId() : "null") +
                '}';
    }
}
