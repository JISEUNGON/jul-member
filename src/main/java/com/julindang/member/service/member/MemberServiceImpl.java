package com.julindang.member.service.member;

import com.julindang.member.domain.Authority;
import com.julindang.member.domain.Member;
import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.SignUpRequestDto;
import com.julindang.member.dto.response.login.LoginResponseDto;
import com.julindang.member.exception.member.MemberIdNotFoundException;
import com.julindang.member.repository.MemberRepository;
import com.julindang.member.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.julindang.member.config.MapperConfig.modelMapper;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final String PHONE_NUMBER;

    public MemberServiceImpl(MemberRepository memberRepository, @Value("${coolsms.key}")String API_KEY, @Value("${coolsms.secret}") String secret,
                             @Value("${coolsms.phone}") String ph) {
        this.memberRepository = memberRepository;

        PHONE_NUMBER = ph;
    }

    @Override
    @Transactional
    public LoginResponseDto login(final LoginRequestDto dto) {
        final Optional<Member> byEmail = memberRepository.findByEmail(dto.getEmail());

        if(byEmail.isEmpty())
            return null;
        else
            return modelMapper.map(byEmail, LoginResponseDto.class);
    }

    @Override
    @Transactional
    public Boolean checkDuplicatedNickname(final String nickname) {
        final Optional<Member> byNickname = memberRepository.findByNickname(nickname);

        return byNickname.isEmpty() ? false : true;
    }

    @Override
    @Transactional
    public LoginResponseDto signUp(final SignUpRequestDto dto) {
        final long memberId = System.currentTimeMillis();
        final Member save = memberRepository.save(
                Member.builder()
                        .memberId(memberId)
                        .email(dto.getEmail())
                        .age(dto.getAge())
                        .gender(dto.getGender())
                        .nickname(dto.getNickname())
                        .phoneNumber(dto.getPhoneNumber())
                        .height(dto.getHeight())
                        .weight(dto.getWeight())
                        .marketing(dto.getMarketing())
                        .loginType(dto.getLoginType())
                        .deleted(false)
                        .accessToken(JwtUtil.createJwt(memberId, dto.getEmail()))
                        .accessTokenExpiredAt(LocalDate.now().plusYears(1L))
                        .refreshToken(JwtUtil.createRefreshToken(memberId))
                        .refreshTokenExpiredAt(LocalDate.now().plusYears(1L))
                        .build()
        );

        return modelMapper.map(save, LoginResponseDto.class);
    }

    @Override
    public Set<Authority> getAuthorities() {
        return memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        ).getAuthorities();
    }

    @Override
    @Transactional
    public Set<Authority> getAuthorities(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        ).getAuthorities();
    }

    @Override
    @Transactional
    public Set<Authority> upgrade(final String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        Set<Authority> authorities = member.getAuthorities();

        authorities.add(Authority.builder()
                        .authorityName("ROLE_ADMIN")
                        .build());

        return memberRepository.save(
                member
        ).getAuthorities();
    }

    @Override
    @Transactional
    public Set<Authority> downgrade(final String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        Set<Authority> authorities = member.getAuthorities();

        authorities.remove(Authority.builder()
                        .authorityName("ROLE_ADMIN")
                .build());

        return memberRepository.save(
                member
        ).getAuthorities();
    }
}
