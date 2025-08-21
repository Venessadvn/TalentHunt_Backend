// src/main/java/com/code/api/models/VideoDTO.java
package com.code.api.dto;

public class VideoDTO {
    private int submissionId;
    private String videoUrl;
    private String submissionDate;
    private String videoDescription;
    private int contestantId;

    // Constructors
    public VideoDTO() {}

    public VideoDTO(int submissionId, String videoUrl, String submissionDate, String videoDescription, int contestantId) {
        this.submissionId = submissionId;
        this.videoUrl = videoUrl;
        this.submissionDate = submissionDate;
        this.videoDescription = videoDescription;
        this.contestantId = contestantId;
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

    public int getContestantId() {
        return contestantId;
    }

    public void setContestantId(int contestantId) {
        this.contestantId = contestantId;
    }
}
