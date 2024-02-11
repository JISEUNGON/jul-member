package com.julindang.member.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberProfileEditRequestDto {
    private Boolean gender;
    private Long age;
    private Long height;
    private Long weight;
}
