package com.julindang.member.dto.request.login;

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
public class SignUpRequestDto {
    private Boolean gender; //true가 남자, false가 여자
    private Long age;
    private Long height;
    private Long weight;
    private Boolean marketing;
    private String nickname;
    private Long phoneNumber;
    private String email;
    private Long loginType;
    private List<RiskItem> riskItemList;
}
