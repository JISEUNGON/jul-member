package com.julindang.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "risk_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RiskItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "int")
    private Long itemId;

    @Column(name = "item_name", columnDefinition = "varchar", length = 20)
    private String itemName;

    @Column(name = "score", columnDefinition = "int")
    private Long score;

    @Column(name = "risk_id", columnDefinition = "int")
    private Long riskId;
}
