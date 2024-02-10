package com.julindang.member.service.member;

import com.julindang.member.domain.Member;
import com.julindang.member.dto.request.login.LoginRequestDto;
import com.julindang.member.dto.request.login.SignUpRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberRequestDto;
import com.julindang.member.dto.request.login.VerifyPhoneNumberResponseDto;
import com.julindang.member.dto.response.login.LoginResponseDto;
import com.julindang.member.repository.MemberRepository;
import com.julindang.member.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static com.julindang.member.config.MapperConfig.modelMapper;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final DefaultMessageService messageService;
    private static Map<String, Integer> codeList;
    private final String PHONE_NUMBER;

    public MemberServiceImpl(MemberRepository memberRepository, @Value("${coolsms.key}")String API_KEY, @Value("${coolsms.secret}") String secret,
                             @Value("${coolsms.phone}") String ph) {
        this.memberRepository = memberRepository;

        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, secret, "https://api.coolsms.co.kr");

        codeList = new HashMap<>();

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
    public String pushVerifyCode(final VerifyPhoneNumberRequestDto phoneNumber) {
        final String ph = "0" + phoneNumber.getPhoneNumber();

        final int code = ThreadLocalRandom.current().nextInt(100000, 1000000);

        codeList.put(ph, code);

        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(PHONE_NUMBER);
        message.setTo(ph);
        message.setText("[줄인당]\n본인확인 인증번호 [" + code + "]를 입력해 주세요.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return "code: " + codeList.get(ph);
    }

    @Override
    @Transactional
    public Boolean verifyCode(final VerifyPhoneNumberResponseDto dto) {
        return this.codeList.get("0" + dto.getPhoneNumber()).equals(dto.getCode()) ? true : false;
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
}
