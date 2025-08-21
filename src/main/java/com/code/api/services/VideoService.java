package com.code.api.services;

import com.code.api.models.VideoSubmission;
import com.code.api.reposatories.VideoSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoSubmissionRepository videoSubmissionRepository;

    public List<VideoSubmission> getTop3Videos() {
        // Get top 3 videos based on totalScore of contestant
        return videoSubmissionRepository.findTopVideosByScore(PageRequest.of(0, 3));
    }
}
