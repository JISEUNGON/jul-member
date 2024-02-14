package com.julindang.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "diabetes_risk")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DiabetesRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "risk_id", columnDefinition = "int")
    private Long riskId;

    @Column(name = "item_name", columnDefinition = "varchar", length = 100)
    private String itemName;

    @Column(name = "item_type", columnDefinition = "int")
    private Long itemType;

    @Column(name = "gender", columnDefinition = "int")
    private Long gender;

    @Column(name = "updated_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "admin_name", columnDefinition = "varchar", length = 30)
    private String adminName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "risk_id")
    @OrderBy("itemId")
    private Set<RiskItem> items;
}
