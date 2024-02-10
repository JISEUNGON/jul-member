package com.julindang.member.controller;

import com.julindang.member.dto.response.diabetes.DiabetesAllResponseDto;
import com.julindang.member.service.diabets.DiabetesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etc")
@RequiredArgsConstructor
@Slf4j
public class EtcController {
    private final DiabetesService diabetesService;

    @GetMapping("/diabetes/male")
    public ResponseEntity<List<DiabetesAllResponseDto>> getMaleDiabetes() {
        return ResponseEntity.ok(diabetesService.getMaleDiabetes());
    }

    @GetMapping("/diabetes/female")
    public ResponseEntity<List<DiabetesAllResponseDto>> getFemaleDiabetes() {
        return ResponseEntity.ok(diabetesService.getFemaleDiabetes());
    }
}
