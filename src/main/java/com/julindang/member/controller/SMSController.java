package com.julindang.member.controller;

import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import com.julindang.member.service.sms.SmsService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Balance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Slf4j
public class SMSController {
    private final SmsService smsService;

    @PostMapping("/phone")
    @Parameter(description = "1012341234")
    public ResponseEntity<String> pushCode(@RequestBody VerifyPhoneNumberRequestDto phoneNumber) {
        return ResponseEntity.ok(smsService.pushVerifyCode(phoneNumber));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyPhoneNumberResponseDto dto) {
        final Boolean result = smsService.verifyCode(dto);

        if(result)
            return ResponseEntity.ok("핸드폰 인증 성공.");
        else
            return new ResponseEntity<>("인증번호가 틀림", HttpStatus.NOT_FOUND);
    }

    /**
     * 메시지 잔액 조회
     */
    @GetMapping("/balance")
    public ResponseEntity<Balance> getMessageList() {
        return ResponseEntity.ok(smsService.getBalance());
    }
}
