package com.julindang.member.service.diabets;

import com.julindang.member.domain.DiabetesRisk;
import com.julindang.member.dto.response.diabetes.DiabetesAllResponseDto;
import com.julindang.member.repository.DiabetesRiskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiabetesServiceImpl implements DiabetesService {
    private final DiabetesRiskRepository diabetesRiskRepository;

    @Override
    @Transactional
    public List<DiabetesAllResponseDto> getMaleDiabetes() {
        List<DiabetesAllResponseDto> responseDtoList = new ArrayList<>();

        for (DiabetesRisk diabetesRisk : diabetesRiskRepository.findByMaleDiabetes()) {
            responseDtoList.add(
                    DiabetesAllResponseDto.builder()
                            .itemName(diabetesRisk.getItemName())
                            .itemType(diabetesRisk.getItemType())
                            .items(diabetesRisk.getItems())
                            .build()
            );
        }

        return responseDtoList;
    }

    @Override
    @Transactional
    public List<DiabetesAllResponseDto> getFemaleDiabetes() {
        List<DiabetesAllResponseDto> responseDtoList = new ArrayList<>();

        for (DiabetesRisk diabetesRisk : diabetesRiskRepository.findByFemaleDiabetes()) {
            responseDtoList.add(
                    DiabetesAllResponseDto.builder()
                            .itemName(diabetesRisk.getItemName())
                            .itemType(diabetesRisk.getItemType())
                            .items(diabetesRisk.getItems())
                            .build()
            );
        }

        return responseDtoList;
    }
}
