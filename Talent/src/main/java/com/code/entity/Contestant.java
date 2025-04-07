package com.code.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contestant")
public class Contestant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contestant_id")
    private int contestantId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "category", nullable = false, length = 30)
    private String category;

    // Default constructor
    public Contestant() {
        this.name = null;
        this.email = null;
        this.category = null;
    }

    // Param constructor
    public Contestant(String name, String email, String category) {
        this.name = name;
        this.email = email;
        this.category = category;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Contestant [contestantId=" + contestantId + ", name=" + name + ", email=" + email + ", category=" + category + "]";
    }
}
