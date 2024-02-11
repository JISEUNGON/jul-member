package com.julindang.member.dto.response.diabetes;

import com.julindang.member.domain.RiskItem;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DiabetesAllResponseDto {
    private String itemName;
    private Long itemType;
    private Set<RiskItem> items;
}
