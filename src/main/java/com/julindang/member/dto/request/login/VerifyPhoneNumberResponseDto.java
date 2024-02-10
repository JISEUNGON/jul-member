package com.julindang.member.dto.request.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerifyPhoneNumberResponseDto {
    private Long phoneNumber;
    private Integer code;
}
