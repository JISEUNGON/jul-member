package com.julindang.member.dto.response.member;

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
public class MemberInfoResponseDto {
    private String nickname;
    private String email;
    private Boolean gender;
    private Long age;
    private Long height;
    private Long weight;
    private List<RiskItem> riskItemList;
}
