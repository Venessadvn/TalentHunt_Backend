package com.code.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "contestants")
public class Contestant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contestant_id")
    private int contestantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

 // ManyToOne Category relationship
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    // OneToMany JudgeScore relationship
    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<JudgeScore> judgeScores;

    public Contestant() {
        // Default constructor
    }

    public Contestant(String name, String email, String password, Category category) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    // Method to calculate total score
    public double getTotalScore() {
        return judgeScores == null ? 0.0 :
                judgeScores.stream()
                           .mapToDouble(JudgeScore::getScore)
                           .sum();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<JudgeScore> getJudgeScores() {
        return judgeScores;
    }

    public void setJudgeScores(List<JudgeScore> judgeScores) {
        this.judgeScores = judgeScores;
    }
}
