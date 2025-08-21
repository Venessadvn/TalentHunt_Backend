package com.code.api.controllers;

import com.code.api.models.VideoSubmission;
import com.code.api.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*") // Adjust based on frontend
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/top3")
    public List<VideoSubmission> getTop3Videos() {
        return videoService.getTop3Videos();
    }
}
