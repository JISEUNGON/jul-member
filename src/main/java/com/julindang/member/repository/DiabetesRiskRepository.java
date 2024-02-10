package com.julindang.member.repository;

import com.julindang.member.domain.DiabetesRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiabetesRiskRepository extends JpaRepository<DiabetesRisk, Long> {
    @Query("select d from DiabetesRisk d where d.gender = 0 or d.gender = 1")
    public List<DiabetesRisk> findByMaleDiabetes();

    @Query("select d from DiabetesRisk d where d.gender = 0 or d.gender = 2")
    public List<DiabetesRisk> findByFemaleDiabetes();
}
