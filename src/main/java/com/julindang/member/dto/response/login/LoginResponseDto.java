package com.julindang.member.dto.response.login;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long memberId;
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String email;
    private LocalDate accessTokenExpiredAt;
    private LocalDate refreshTokenExpiredAt;
}