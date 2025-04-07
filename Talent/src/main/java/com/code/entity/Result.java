package com.code.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;

    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

    @Column(name = "ranking")
    private int ranking;

    public Result() {}

    public Result(Submission submission, int totalScore, int rank) {
        this.submission = submission;
        this.totalScore = totalScore;
        this.ranking = ranking;
    }

    // Getters and Setters
    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRank() {
        return ranking;
    }

    public void setRank(int rank) {
        this.ranking = rank;
    }

    @Override
    public String toString() {
        return "Result [resultId=" + resultId + ", submission=" + submission.getTitle()
                + ", totalScore=" + totalScore + ", rank=" + ranking + "]";
    }
}
