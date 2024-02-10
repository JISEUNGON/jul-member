package com.julindang.member.controller;

import com.julindang.member.domain.Authority;
import com.julindang.member.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final MemberService memberService;

    @GetMapping("/check")
    public ResponseEntity<Set<Authority>> getAuthorities() {
        return ResponseEntity.ok(memberService.getAuthorities());
    }

    @GetMapping("/check-one")
    public ResponseEntity<Set<Authority>> getAuthorities(@RequestParam String email) {
        return ResponseEntity.ok(memberService.getAuthorities(email));
    }

    @GetMapping("/upgrade")
    public ResponseEntity<Set<Authority>> upgrade(@RequestParam String email) {
        return ResponseEntity.ok(memberService.upgrade(email));
    }

    @GetMapping("/downgrade")
    public ResponseEntity<Set<Authority>> downgrade(@RequestParam String email) {
        return ResponseEntity.ok(memberService.downgrade(email));
    }
}
