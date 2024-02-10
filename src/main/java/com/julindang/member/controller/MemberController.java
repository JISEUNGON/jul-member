package com.julindang.member.controller;

import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import com.julindang.member.dto.response.login.LoginResponseDto;
import com.julindang.member.service.member.MemberService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "access token dto"),
            @ApiResponse(responseCode = "200", description = "null")
    })
    public ResponseEntity<LoginResponseDto> signUp(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(memberService.login(dto));
    }

    @GetMapping("/nickname")
    public ResponseEntity<String> checkDuplicated(@RequestParam String nickname) {
        final Boolean isDuplicated = memberService.checkDuplicatedNickname(nickname);

        if(isDuplicated)
            return ResponseEntity.ok("사용 가능한 닉네임입니다.");
        else
            return new ResponseEntity<>("중복된 닉네임입니다.", HttpStatus.CONFLICT);
    }

    @PostMapping("/phone")
    @Parameter(description = "1012341234")
    public ResponseEntity<String> pushCode(@RequestBody VerifyPhoneNumberRequestDto phoneNumber) {
        return ResponseEntity.ok(memberService.pushVerifyCode(phoneNumber));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyPhoneNumberResponseDto dto) {
        final Boolean result = memberService.verifyCode(dto);

        if(result)
            return ResponseEntity.ok("핸드폰 인증 성공.");
        else
            return new ResponseEntity<>("인증번호가 틀림", HttpStatus.NOT_FOUND);
    }
}
