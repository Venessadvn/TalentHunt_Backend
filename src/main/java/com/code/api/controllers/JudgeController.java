package com.code.api.controllers;

import com.code.api.dto.JudgeRegistrationDTO;
import com.code.api.dto.JudgeResponseDTO;
import com.code.api.dto.LoginDTO;
import com.code.api.models.Judge;
import com.code.api.services.JudgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/judges")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    // ✅ Register a new judge
    @PostMapping("/register")
    public ResponseEntity<JudgeResponseDTO> registerJudge(@RequestBody JudgeRegistrationDTO dto) {
        Judge judge = new Judge();
        judge.setName(dto.getName());
        judge.setExpertise(dto.getExpertise());
        judge.setEmail(dto.getEmail());
        judge.setPassword(dto.getPassword()); // Note: Ideally, hash this

        Judge savedJudge = judgeService.createJudge(judge);

        JudgeResponseDTO responseDTO = new JudgeResponseDTO(
                savedJudge.getJudgeId(),
                savedJudge.getName(),
                savedJudge.getExpertise(),
                savedJudge.getEmail()
        );

        return ResponseEntity.ok(responseDTO);
    }

    // ✅ Get all judges
    @GetMapping
    public ResponseEntity<List<JudgeResponseDTO>> getAllJudges() {
        List<Judge> judges = judgeService.getAllJudges();
        List<JudgeResponseDTO> dtoList = judges.stream()
                .map(j -> new JudgeResponseDTO(j.getJudgeId(), j.getName(), j.getExpertise(), j.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // ✅ Get judge by ID
    @GetMapping("/{id}")
    public ResponseEntity<JudgeResponseDTO> getJudgeById(@PathVariable int id) {
        Optional<Judge> judgeOpt = judgeService.getJudgeById(id);
        if (judgeOpt.isEmpty()) return ResponseEntity.notFound().build();

        Judge j = judgeOpt.get();
        JudgeResponseDTO dto = new JudgeResponseDTO(j.getJudgeId(), j.getName(), j.getExpertise(), j.getEmail());
        return ResponseEntity.ok(dto);
    }

    // ✅ Login judge
    @PostMapping("/login")
    public ResponseEntity<?> loginJudge(@RequestBody LoginDTO loginDTO) {
        Optional<Judge> judgeOpt = judgeService.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (judgeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        Judge judge = judgeOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("judgeId", judge.getJudgeId());
        response.put("name", judge.getName());
        response.put("email", judge.getEmail());
        response.put("role", "judge"); // 🚀 This line ensures the frontend sees judge access

        return ResponseEntity.ok(response);
    }

    // ✅ Check if email already exists
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(judgeService.judgeExistsByEmail(email));
    }

    // ✅ Delete judge by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJudge(@PathVariable int id) {
        judgeService.deleteJudge(id);
        return ResponseEntity.noContent().build();
    }
}
