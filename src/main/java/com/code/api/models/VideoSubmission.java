package com.code.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "video_submissions")
public class VideoSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private int submissionId;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "contestant_id", nullable = false)
    private Contestant contestant;

    @Column(name = "video_url", nullable = false, length = 500)
    private String videoUrl;

    @Column(name = "submission_date", nullable = false)
    private String submissionDate;

    @Column(name = "video_description", length = 1000)
    private String videoDescription;

    public VideoSubmission() {
    }

    public VideoSubmission(Contestant contestant, String videoUrl, String submissionDate, String videoDescription) {
        this.contestant = contestant;
        this.videoUrl = videoUrl;
        this.submissionDate = submissionDate;
        this.videoDescription = videoDescription;
    }

    // Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    @Override
    public String toString() {
        return "VideoSubmission{" +
                "submissionId=" + submissionId +
                ", contestant=" + (contestant != null ? contestant.getContestantId() : "null") +
                ", videoUrl='" + videoUrl + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                '}';
    }
}
