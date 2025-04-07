package com.code.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private int evaluationId;

    @ManyToOne
    @JoinColumn(name = "judge_id", nullable = false)
    private Judge judge;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment", length = 300)
    private String comment;

    public Evaluation() {}

    public Evaluation(Judge judge, Submission submission, int score, String comment) {
        this.judge = judge;
        this.submission = submission;
        this.score = score;
        this.comment = comment;
    }

    // Getters and Setters
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Evaluation [evaluationId=" + evaluationId + ", judge=" + judge.getName()
                + ", submission=" + submission.getTitle() + ", score=" + score + ", comment=" + comment + "]";
    }
}
