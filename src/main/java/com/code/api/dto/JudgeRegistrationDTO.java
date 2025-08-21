package com.code.api.dto;

public class JudgeRegistrationDTO {
    private String name;
    private String expertise;
    private String email;
    private String password;

    public JudgeRegistrationDTO() {}

    public JudgeRegistrationDTO(String name, String expertise, String email, String password) {
        this.name = name;
        this.expertise = expertise;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
