package com.code.api.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int submissionId;

    private String videoUrl;
    private String videoDescription;

    private LocalDateTime submissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contestant_id")
    private Contestant contestant;

    public Submission() {}

    // Constructor with fields except ID
    public Submission(String videoUrl, String videoDescription, LocalDateTime submissionDate, Contestant contestant) {
        this.videoUrl = videoUrl;
        this.videoDescription = videoDescription;
        this.submissionDate = submissionDate;
        this.contestant = contestant;
    }

    // Getters and Setters

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }
}
