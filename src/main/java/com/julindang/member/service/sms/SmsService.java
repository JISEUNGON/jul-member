package com.julindang.member.service.sms;

import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface SmsService {
    @Transactional
    String pushVerifyCode(VerifyPhoneNumberRequestDto phoneNumber);

    @Transactional
    Boolean verifyCode(VerifyPhoneNumberResponseDto dto);
}
