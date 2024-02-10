package com.julindang.member.service.member;

import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.SignUpRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import com.julindang.member.dto.response.login.LoginResponseDto;

public interface MemberService {
    public LoginResponseDto login(LoginRequestDto dto);
    public Boolean checkDuplicatedNickname(String nickname);
    public String pushVerifyCode(VerifyPhoneNumberRequestDto phoneNumber);
    public Boolean verifyCode(VerifyPhoneNumberResponseDto dto);
    public LoginResponseDto signUp(SignUpRequestDto dto);
}
