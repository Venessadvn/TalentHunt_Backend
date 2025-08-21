package com.code.api.controllers;

import com.code.api.models.Submission;
import com.code.api.models.Contestant;
import com.code.api.reposatories.SubmissionRepository;
import com.code.api.reposatories.ContestantRepository;
import com.code.api.dto.SubmissionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin(origins = "*")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepo;

    @Autowired
    private ContestantRepository contestantRepo;

    // Add new submission
    @PostMapping("/add")
    public ResponseEntity<?> addSubmission(@RequestBody SubmissionDTO submissionDTO) {

        Optional<Contestant> contestantOpt = contestantRepo.findById(submissionDTO.getContestantId());
        if (!contestantOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid contestant ID");
        }

        Submission submission = new Submission();
        submission.setContestant(contestantOpt.get());
        submission.setVideoUrl(submissionDTO.getVideoUrl());
        submission.setVideoDescription(submissionDTO.getVideoDescription());
        submission.setSubmissionDate(submissionDTO.getSubmissionDate());

        submissionRepo.save(submission);

        return ResponseEntity.ok("Submission added successfully");
    }

    // Get all submissions for a contestant by contestant ID
    @GetMapping("/contestant/{contestantId}")
    public ResponseEntity<?> getSubmissionsByContestant(@PathVariable int contestantId) {

        Optional<Contestant> contestantOpt = contestantRepo.findById(contestantId);
        if (!contestantOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Contestant not found");
        }

        List<Submission> submissions = submissionRepo.findByContestantOrderBySubmissionDateDesc(contestantOpt.get());
        return ResponseEntity.ok(submissions);
    }
}
