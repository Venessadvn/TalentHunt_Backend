package com.code.api.models;

import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private String category;
    private List<Contestant> allContestantsSorted;
    private List<Contestant> top3Contestants;

    public Result(String category, List<Contestant> contestants) {
        this.category = category;
        this.allContestantsSorted = sortByScoreDescending(contestants);
        this.top3Contestants = calculateTop3Contestants();
    }

    private List<Contestant> sortByScoreDescending(List<Contestant> list) {
        return list.stream()
                .sorted((c1, c2) -> Double.compare(c2.getTotalScore(), c1.getTotalScore()))
                .collect(Collectors.toList());
    }

    private List<Contestant> calculateTop3Contestants() {
        return allContestantsSorted.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    // Getters and Setters

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Contestant> getAllContestantsSorted() {
        return allContestantsSorted;
    }

    public void setAllContestantsSorted(List<Contestant> contestants) {
        this.allContestantsSorted = sortByScoreDescending(contestants);
        this.top3Contestants = calculateTop3Contestants();
    }

    public List<Contestant> getTop3Contestants() {
        return top3Contestants;
    }

    public void setTop3Contestants(List<Contestant> top3Contestants) {
        this.top3Contestants = top3Contestants;
    }

    @Override
    public String toString() {
        return "Result{" +
                "category='" + category + '\'' +
                ", top3Contestants=" + top3Contestants +
                ", allContestantsSorted=" + allContestantsSorted +
                '}';
    }
}
