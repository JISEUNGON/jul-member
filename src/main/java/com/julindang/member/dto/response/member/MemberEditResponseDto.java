package com.julindang.member.dto.response.member;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberEditResponseDto {
    private Long memberId;
    private String nickname;
    private String phoneNumber;
}
