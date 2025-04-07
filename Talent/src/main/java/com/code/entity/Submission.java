package com.code.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private int submissionId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "video_path", nullable = false)
    private String videoPath;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    // Many submissions can belong to one contestant
    @ManyToOne
    @JoinColumn(name = "contestant_id", nullable = false)
    private Contestant contestant;

    // Default constructor
    public Submission() {
        this.submittedAt = LocalDateTime.now();
    }

    // Parameterized constructor
    public Submission(String title, String category, String videoPath, Contestant contestant) {
        this.title = title;
        this.category = category;
        this.videoPath = videoPath;
        this.contestant = contestant;
        this.submittedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    // toString
    @Override
    public String toString() {
        return "Submission [submissionId=" + submissionId + ", title=" + title + ", category=" + category
                + ", videoPath=" + videoPath + ", submittedAt=" + submittedAt
                + ", contestant=" + contestant.getName() + "]";
    }
}
