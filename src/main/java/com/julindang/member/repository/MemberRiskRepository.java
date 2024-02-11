package com.julindang.member.repository;

import com.julindang.member.domain.MemberRisk;
import com.julindang.member.domain.RiskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRiskRepository extends JpaRepository<MemberRisk, Long> {
    public List<RiskItem> findByMemberId(Long memberId);
}
