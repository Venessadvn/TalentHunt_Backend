package com.code.api.services;

import com.code.api.models.Category;
import com.code.api.models.Contestant;
import com.code.api.models.Result;
import com.code.api.reposatories.CategoryRepository;
import com.code.api.reposatories.ContestantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ContestantRepository contestantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Get top 3 and full ranking for a specific category wrapped in Result
    public Result getTop3ByCategory(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByCatname(categoryName);

        if (categoryOpt.isEmpty()) {
            return new Result(categoryName, List.of());  // Empty result if category not found
        }

        Category category = categoryOpt.get();
        List<Contestant> contestants = contestantRepository.findByCategory(category);

        return new Result(categoryName, contestants); // Sorting handled in Result class
    }

    // 2. Get top 3 overall wrapped in Result
    public Result getTop3OverallResult() {
        List<Contestant> allContestants = contestantRepository.findAll();

        return new Result("All", allContestants); // "All" as category label
    }

    // 3. Get all contestants ranked globally (no category)
    public List<Contestant> getAllRanked() {
        return contestantRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Contestant::getTotalScore).reversed())
                .toList();
    }

    // 4. Get all contestants ranked by category
    public List<Contestant> getRankingByCategory(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByCatname(categoryName);

        if (categoryOpt.isEmpty()) {
            return List.of();
        }

        List<Contestant> contestants = contestantRepository.findByCategory(categoryOpt.get());

        return contestants.stream()
                .sorted(Comparator.comparingDouble(Contestant::getTotalScore).reversed())
                .toList();
    }
}
