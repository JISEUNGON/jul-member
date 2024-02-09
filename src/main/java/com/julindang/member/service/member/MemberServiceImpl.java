package com.julindang.member.service.member;

import com.julindang.member.domain.Member;
import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.response.login.LoginResponseDto;
import com.julindang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.julindang.member.config.MapperConfig.modelMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

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
}
