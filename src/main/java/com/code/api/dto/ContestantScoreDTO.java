package com.code.api.dto;

public class ContestantScoreDTO {
    private int contestantId;
    private String name;
    private String category;
    private double totalScore;

    public ContestantScoreDTO(int contestantId, String name, String category, double totalScore) {
        this.contestantId = contestantId;
        this.name = name;
        this.category = category;
        this.totalScore = totalScore;
    }

    // Getters and Setters
    public int getContestantId() {
        return contestantId;
    }

    public void setContestantId(int contestantId) {
        this.contestantId = contestantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
}
