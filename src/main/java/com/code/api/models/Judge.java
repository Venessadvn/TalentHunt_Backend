package com.code.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "judges")
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "judge_id")
    private int judgeId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "expertise", nullable = false, length = 50)
    private String expertise;

    // Add login info here if needed, for example:
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // Constructors
    public Judge() {
        this.judgeId = 0;
        this.name = null;
        this.expertise = null;
        this.email = null;
        this.password = null;
    }

    public Judge(String name, String expertise, String email, String password) {
        this.name = name;
        this.expertise = expertise;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
