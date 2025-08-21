package com.code.api.dto;

import java.time.LocalDateTime;

public class SubmissionDTO {

    private int contestantId;
    private String videoUrl;
    private String videoDescription;
    private LocalDateTime submissionDate;

    public SubmissionDTO() {}

    public SubmissionDTO(int contestantId, String videoUrl, String videoDescription, LocalDateTime submissionDate) {
        this.contestantId = contestantId;
        this.videoUrl = videoUrl;
        this.videoDescription = videoDescription;
        this.submissionDate = submissionDate;
    }

    public int getContestantId() {
        return contestantId;
    }

    public void setContestantId(int contestantId) {
        this.contestantId = contestantId;
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
}
