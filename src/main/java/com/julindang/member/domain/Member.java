package com.julindang.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Member {
    @Id
    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "nickname", columnDefinition = "varchar", length = 20)
    private String nickname;

    @Column(name = "email", columnDefinition = "varchar", length = 30)
    private String email;

    @Column(name = "login_type", columnDefinition = "int")
    private Long loginType;

    @Column(name = "gender", columnDefinition = "tinyint")
    private Boolean gender;

    @Column(name = "age", columnDefinition = "int")
    private Long age;

    @Column(name = "height", columnDefinition = "double")
    private Double height;

    @Column(name = "weight", columnDefinition = "double")
    private Double weight;

    @Column(name = "phone_number", columnDefinition = "int")
    private Long phoneNumber;

    @Column(name = "access_token", columnDefinition = "text")
    private String accessToken;

    @Column(name = "access_token_expired_at", columnDefinition = "date")
    private LocalDate accessTokenExpiredAt;

    @Column(name = "refresh_token", columnDefinition = "text")
    private String refreshToken;

    @Column(name = "refresh_token_expired_at", columnDefinition = "date")
    private LocalDate refreshTokenExpiredAt;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime")
    private LocalDateTime createdAt;
}
