package com.taw.polybank.dao;

import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.BenficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Illya Rozumovskyy 50%
 * @author Lucía Gutiérrez Molina 50%
 */
@Repository
public interface BeneficiaryRepository extends JpaRepository<BenficiaryEntity, Integer> {

    Optional<BenficiaryEntity> findByIban (String iban);

    BenficiaryEntity findBenficiaryEntityByNameAndIban(String name, String iban);
}
