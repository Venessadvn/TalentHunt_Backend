package com.code.api.controllers;

import com.code.api.dto.VideoDTO;
import com.code.api.models.VideoSubmission;
import com.code.api.services.VideoSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoSubmissionController {

    @Autowired
    private VideoSubmissionService videoSubmissionService;

    @GetMapping
    public List<VideoSubmission> getAllSubmissions() {
        return videoSubmissionService.getAllSubmissions();
    }

    @GetMapping("/contestant/{id}")
    public List<VideoSubmission> getSubmissionsByContestantId(@PathVariable int id) {
        return videoSubmissionService.getSubmissionsByContestantId(id);
    }

    @GetMapping("/dtos")
    public List<VideoDTO> getAllVideoDTOs() {
        return videoSubmissionService.getAllSubmissionDTOs();
    }

    @PutMapping("/update/{id}")
    public VideoSubmission updateVideoDescription(
            @PathVariable int id,
            @RequestParam String videoDescription) {
        return videoSubmissionService.updateVideoDescription(id, videoDescription);
    }

    // POST method using @RequestParam for URL parameters (Option 1)
    @PostMapping("/submit")
    public ResponseEntity<?> submitVideo(
            @RequestParam int contestantId,
            @RequestParam String videoUrl,
            @RequestParam String submissionDate,
            @RequestParam(required = false) String videoDescription
    ) {
        try {
            VideoSubmission saved = videoSubmissionService.submitVideo(contestantId, videoUrl, submissionDate, videoDescription);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
