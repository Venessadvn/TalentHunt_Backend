package com.code.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.api.models.Vote;
import com.code.api.reposatories.*;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    // Method to save a vote
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    // Method to get all votes for a specific contestant
    public List<Vote> getVotesByContestantId(int contestantId) {
        return voteRepository.findAll().stream()
                .filter(vote -> vote.getContestant().getContestantId() == contestantId)
                .toList();
    }

    // Method to get vote by ID
    public Optional<Vote> getVoteById(int voteId) {
        return voteRepository.findById(voteId);
    }

	public List<Vote> getAllVotes() {
		// TODO Auto-generated method stub
		return null;
	}
    

    // Additional methods can be added as per requirements
}
