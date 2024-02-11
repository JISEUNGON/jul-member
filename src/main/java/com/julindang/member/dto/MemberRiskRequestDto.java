package com.julindang.member.dto;

import com.julindang.member.domain.RiskItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberRiskRequestDto {
    private List<RiskItem> riskItemList;
}
