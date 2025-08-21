package com.code.api.controllers;

import com.code.api.dto.TopContestantDTO;
import com.code.api.models.Contestant;
import com.code.api.models.VideoSubmission;
import com.code.api.services.ResultService;
import com.code.api.reposatories.VideoSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private VideoSubmissionRepository videoSubmissionRepository;

    // ✅ Top 3 by Category
    @GetMapping("/top3/{category}")
    public List<TopContestantDTO> getTop3ContestantsByCategory(@PathVariable String category) {
        List<Contestant> top3 = resultService.getRankingByCategory(category).stream().limit(3).toList();
        return convertToTopDTO(top3);
    }

    // ✅ Top 3 Overall (with video URLs)
    @GetMapping("/top3")
    public List<TopContestantDTO> getTop3Overall() {
        List<Contestant> top3 = resultService.getAllRanked().stream().limit(3).toList();
        return convertToTopDTO(top3);
    }

    // ✅ Overall Rankings
    @GetMapping("/all-rankings")
    public List<Contestant> getAllRankings() {
        return resultService.getAllRanked();
    }

    // ✅ Rankings by Category
    @GetMapping("/ranking/{category}")
    public List<Contestant> getRankingByCategory(@PathVariable String category) {
        return resultService.getRankingByCategory(category);
    }

    // ✅ Helper Method to convert to DTO with video URL
    private List<TopContestantDTO> convertToTopDTO(List<Contestant> contestants) {
        List<TopContestantDTO> dtoList = new ArrayList<>();

        for (Contestant contestant : contestants) {
            TopContestantDTO dto = new TopContestantDTO();
            dto.setContestantId(contestant.getContestantId());
            dto.setName(contestant.getName());
            dto.setEmail(contestant.getEmail());
            dto.setCategory(contestant.getCategory().getCatname());
            dto.setTotalScore(contestant.getTotalScore());

            List<VideoSubmission> videos = videoSubmissionRepository.findByContestant(contestant);
            if (!videos.isEmpty()) {
                dto.setVideoUrl(videos.get(0).getVideoUrl()); // Just take the first video
            } else {
                dto.setVideoUrl(null);
            }

            dtoList.add(dto);
        }

        return dtoList;
    }
}
