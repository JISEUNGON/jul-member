package com.julindang.member.service.member;

import com.julindang.member.domain.Authority;
import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.SignUpRequestDto;
import com.julindang.member.dto.response.login.LoginResponseDto;

import java.util.List;
import java.util.Set;

public interface MemberService {
    public LoginResponseDto login(LoginRequestDto dto);
    public Boolean checkDuplicatedNickname(String nickname);
    public LoginResponseDto signUp(SignUpRequestDto dto);
    public Set<Authority> getAuthorities();
    public Set<Authority> getAuthorities(String email);
    public Set<Authority> upgrade(String email);
    public Set<Authority> downgrade(String email);
}
