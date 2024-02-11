package com.julindang.member.controller;

import com.julindang.member.dto.MemberRiskRequestDto;
import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.SignUpRequestDto;
import com.julindang.member.dto.request.member.MemberEditRequestDto;
import com.julindang.member.dto.request.member.MemberProfileEditRequestDto;
import com.julindang.member.dto.response.login.LoginResponseDto;
import com.julindang.member.dto.response.member.MemberEditResponseDto;
import com.julindang.member.service.member.MemberService;
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

    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        return ResponseEntity.ok(memberService.signUp(dto));
    }

    @PostMapping("/edit")
    public ResponseEntity<MemberEditResponseDto> edit(@RequestBody MemberEditRequestDto dto) {
        return ResponseEntity.ok(memberService.edit(dto));
    }

    @PostMapping("/profile/edit")
    public ResponseEntity editProfile(@RequestBody MemberProfileEditRequestDto dto) {
        memberService.edit(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/risk/edit")
    public ResponseEntity editRisk(@RequestBody MemberRiskRequestDto dto) {
        memberService.edit(dto);
        return ResponseEntity.ok().build();
    }
}
