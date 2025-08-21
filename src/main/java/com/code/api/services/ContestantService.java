package com.code.api.services;

import com.code.api.models.Category;
import com.code.api.models.Contestant;
import com.code.api.reposatories.CategoryRepository;
import com.code.api.reposatories.ContestantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContestantService {

    @Autowired
    private ContestantRepository contestantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Contestant> getAllContestants() {
        return contestantRepository.findAll();
    }

    public Contestant getContestantById(int id) {
        Optional<Contestant> contestant = contestantRepository.findById(id);
        return contestant.orElse(null);
    }

    public List<Contestant> getContestantsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByCatname(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return contestantRepository.findByCategory(category);
    }

    public Contestant saveContestant(Contestant contestant) {
        return contestantRepository.save(contestant);
    }

    public void deleteContestant(int id) {
        contestantRepository.deleteById(id);
    }

    public Contestant updateContestant(Contestant contestant) {
        return contestantRepository.save(contestant);
    }

	public boolean isContestantRegistered(String contestantName, String categoryName) {
		// TODO Auto-generated method stub
		return false;
	}
}
