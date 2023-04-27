package com.taw.polybank.service;

import com.taw.polybank.dao.BeneficiaryRepository;
import com.taw.polybank.dto.BenficiaryDTO;
import com.taw.polybank.entity.BenficiaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenficiaryService {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;
    public BenficiaryDTO findByIban(String beneficiaryIban) {
        BenficiaryEntity benficiaryEntity = beneficiaryRepository.findByIban(beneficiaryIban).orElse(null);
        return benficiaryEntity == null ? null : benficiaryEntity.toDTO();
    }
}
