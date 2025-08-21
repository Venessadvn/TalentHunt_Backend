package com.code.api.dto;

public class JudgeResponseDTO {
    private int judgeId;
    private String name;
    private String expertise;
    private String email;

    public JudgeResponseDTO() {}

    public JudgeResponseDTO(int judgeId, String name, String expertise, String email) {
        this.judgeId = judgeId;
        this.name = name;
        this.expertise = expertise;
        this.email = email;
    }

    // Getters and Setters

    public int getJudgeId() { return judgeId; }
    public void setJudgeId(int judgeId) { this.judgeId = judgeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
