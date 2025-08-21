package com.code.api.services;

import com.code.api.models.Judge;
import com.code.api.reposatories.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JudgeService {

    @Autowired
    private JudgeRepository judgeRepository;

    // Create a new judge
    public Judge createJudge(Judge judge) {
        return judgeRepository.save(judge);
    }

    // Get all judges
    public List<Judge> getAllJudges() {
        return judgeRepository.findAll();
    }

    // Get judge by ID
    public Optional<Judge> getJudgeById(int id) {
        return judgeRepository.findById(id);
    }

    // Update judge
    public Judge updateJudge(int id, Judge judgeDetails) {
        Optional<Judge> judge = judgeRepository.findById(id);
        if (judge.isPresent()) {
            Judge updatedJudge = judge.get();
            updatedJudge.setExpertise(judgeDetails.getExpertise());
            updatedJudge.setName(judgeDetails.getName());
            updatedJudge.setEmail(judgeDetails.getEmail());
            updatedJudge.setPassword(judgeDetails.getPassword());
            return judgeRepository.save(updatedJudge);
        }
        return null;
    }

    // Delete judge by ID
    public void deleteJudge(int id) {
        judgeRepository.deleteById(id);
    }

    // New method to find judge by email and password
    public Optional<Judge> findByEmailAndPassword(String email, String password) {
        return judgeRepository.findByEmailAndPassword(email, password);
    }
    
 // Check if a judge exists by email
    public boolean judgeExistsByEmail(String email) {
        return judgeRepository.findByEmail(email).isPresent();
    }

}
