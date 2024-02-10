package com.julindang.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_risk")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "item_id", columnDefinition = "int")
    private Long itemId;
}
