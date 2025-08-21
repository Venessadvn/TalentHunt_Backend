package com.code.api.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private int voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contestant_id", nullable = false)
    private Contestant contestant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judge_id", nullable = false)
    private Judge judge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", nullable = false)
    private VideoSubmission videoSubmission;

    @Column(name = "score", nullable = false)
    private double score;

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    public Vote() {
    }

    public Vote(Contestant contestant, Judge judge, VideoSubmission videoSubmission, double score, LocalDate voteDate) {
        this.contestant = contestant;
        this.judge = judge;
        this.videoSubmission = videoSubmission;
        this.score = score;
        this.voteDate = voteDate;
    }

    // --- Getters and Setters ---
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
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

    public VideoSubmission getVideoSubmission() {
        return videoSubmission;
    }

    public void setVideoSubmission(VideoSubmission videoSubmission) {
        this.videoSubmission = videoSubmission;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    // --- Convenience Getters ---
    public int getContestantId() {
        return contestant != null ? contestant.getContestantId() : 0;
    }

    public int getVoterId() {
        return judge != null ? judge.getJudgeId() : 0;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", contestant=" + getContestantId() +
                ", judge=" + getVoterId() +
                ", videoSubmission=" + (videoSubmission != null ? videoSubmission.getSubmissionId() : null) +
                ", score=" + score +
                ", voteDate=" + voteDate +
                '}';
    }
}
