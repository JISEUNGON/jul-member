package com.julindang.member.service.sms;

import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.request.MessageListRequest;
import net.nurigo.sdk.message.response.MessageListResponse;
import org.springframework.transaction.annotation.Transactional;

public interface SmsService {
    public String pushVerifyCode(VerifyPhoneNumberRequestDto phoneNumber);
    public Boolean verifyCode(VerifyPhoneNumberResponseDto dto);
    public Balance getBalance();
}
