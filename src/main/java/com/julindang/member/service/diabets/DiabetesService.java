package com.julindang.member.service.diabets;

import com.julindang.member.dto.response.diabetes.DiabetesAllResponseDto;

import java.util.List;

public interface DiabetesService {
    public List<DiabetesAllResponseDto> getMaleDiabetes();
    public List<DiabetesAllResponseDto> getFemaleDiabetes();
}
