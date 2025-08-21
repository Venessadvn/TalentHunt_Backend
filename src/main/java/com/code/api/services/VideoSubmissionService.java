package com.code.api.services;

import com.code.api.models.Contestant;
import com.code.api.models.VideoSubmission;
import com.code.api.reposatories.ContestantRepository;
import com.code.api.reposatories.VideoSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.code.api.dto.VideoDTO;

import java.util.List;



@Service
public class VideoSubmissionService {

    @Autowired
    private VideoSubmissionRepository videoSubmissionRepository;

    @Autowired
    private ContestantRepository contestantRepository;

    public List<VideoSubmission> getAllSubmissions() {
        return videoSubmissionRepository.findAll();
    }
    public VideoSubmission updateVideoDescription(int submissionId, String videoDescription) {
        VideoSubmission submission = videoSubmissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setVideoDescription(videoDescription);
        return videoSubmissionRepository.save(submission);
    }
    
    public List<VideoDTO> getAllSubmissionDTOs() {
        return videoSubmissionRepository.findAll().stream().map(v -> new VideoDTO(
            v.getSubmissionId(),
            v.getVideoUrl(),
            v.getSubmissionDate(),
            v.getVideoDescription(),
            v.getContestant().getContestantId()
        )).collect(Collectors.toList());
    }


    public List<VideoSubmission> getSubmissionsByContestantId(int id) {
        return videoSubmissionRepository.findByContestant_ContestantId(id);
    }

    public VideoSubmission submitVideo(int contestantId, String videoUrl, String submissionDate, String videoDescription) {
        Contestant contestant = contestantRepository.findById(contestantId)
                .orElseThrow(() -> new RuntimeException("Contestant not found with ID: " + contestantId));

        VideoSubmission submission = new VideoSubmission(contestant, videoUrl, submissionDate, videoDescription);
        return videoSubmissionRepository.save(submission);
    }
}
