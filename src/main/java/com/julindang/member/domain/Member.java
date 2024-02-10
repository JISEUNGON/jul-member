package com.julindang.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @Column(name = "height", columnDefinition = "int")
    private Long height;

    @Column(name = "weight", columnDefinition = "int")
    private Long weight;

    @Column(name = "phone_number", columnDefinition = "int")
    private Long phoneNumber;

    @Column(name = "marketing", columnDefinition = "tinyint")
    private Boolean marketing;

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

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
