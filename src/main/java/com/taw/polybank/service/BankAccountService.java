package com.taw.polybank.service;

import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.BankAccountDTO;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<BankAccountDTO> findByClient(ClientDTO client) {
        ClientEntity clientEntity = clientRepository.findByDNI(client.getDni());
        List<BankAccountEntity> bankAccountEntityList = bankAccountRepository.findByClientByClientId(clientEntity);
        return bankAccountEntityListToDTO(bankAccountEntityList);
    }

    private List<BankAccountDTO> bankAccountEntityListToDTO (List<BankAccountEntity> bankAccountEntityList){
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        for (BankAccountEntity bankAccountEntity : bankAccountEntityList){
            bankAccountDTOList.add(bankAccountEntity.toDTO());
        }
        return bankAccountDTOList;
    }

    public BankAccountDTO findById(Integer bankAccountId) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findById(bankAccountId).orElse(null);
        return bankAccountEntity == null ? null : bankAccountEntity.toDTO();
    }

    public BankAccountDTO findByIban(String bankAccountIBAN) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByIban(bankAccountIBAN).orElse(null);
        return bankAccountEntity == null ? null : bankAccountEntity.toDTO();
    }
}
