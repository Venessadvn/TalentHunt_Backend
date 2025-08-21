// 1. FIXED VoteController.java
package com.code.api.controllers;

import com.code.api.models.Vote;
import com.code.api.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // Create or update a vote
    @PostMapping
    public ResponseEntity<?> createVote(@RequestBody Vote vote) {
        try {
            // Add validation
            if (vote.getContestantId() <= 0) {
                return ResponseEntity.badRequest()
                    .body("Invalid contestant ID");
            }
            if (vote.getVoterId() <= 0) {
                return ResponseEntity.badRequest()
                    .body("Invalid voter ID");
            }
            
            Vote savedVote = voteService.saveVote(vote);
            return new ResponseEntity<>(savedVote, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating vote: " + e.getMessage());
        }
    }

    // Get all votes by contestant ID
    @GetMapping("/contestant/{contestantId}")
    public ResponseEntity<?> getVotesByContestantId(@PathVariable int contestantId) {
        try {
            List<Vote> votes = voteService.getVotesByContestantId(contestantId);
            return new ResponseEntity<>(votes, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching votes: " + e.getMessage());
        }
    }

    // Get vote by ID
    @GetMapping("/{voteId}")
    public ResponseEntity<?> getVoteById(@PathVariable int voteId) {
        try {
            Optional<Vote> vote = voteService.getVoteById(voteId);
            return vote.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching vote: " + e.getMessage());
        }
    }

    // Get all votes
    @GetMapping
    public ResponseEntity<?> getAllVotes() {
        try {
            List<Vote> votes = voteService.getAllVotes();
            return ResponseEntity.ok(votes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching all votes: " + e.getMessage());
        }
    }
}