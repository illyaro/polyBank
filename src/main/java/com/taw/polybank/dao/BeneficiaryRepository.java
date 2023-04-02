package com.taw.polybank.dao;

import com.taw.polybank.entity.BenficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<BenficiaryEntity, Integer> {

    public Optional<BenficiaryEntity> findByIban (String iban);
}
