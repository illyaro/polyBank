package com.taw.polybank.service;

import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dao.RequestRepository;
import com.taw.polybank.dto.*;
import com.taw.polybank.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;


    public List<RequestDTO> findByBankAccountByBankAccountIdAndAndSolved(BankAccountDTO bankAccount, byte b) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByIban(bankAccount.getIban()).orElse(null);
        List<RequestEntity> requestEntityList = requestRepository.findByBankAccountByBankAccountIdAndAndSolved(bankAccountEntity, b);
        return entityListToDTO(requestEntityList);
    }

    public List<RequestDTO> entityListToDTO (List<RequestEntity> requestEntityList){
        List<RequestDTO> requestDTOList = new ArrayList<>();
        for(RequestEntity transactionEntity : requestEntityList){
            requestDTOList.add(transactionEntity.toDTO());
        }
        return requestDTOList;
    }

    public void createNewRequest(ClientDTO client, BankAccountDTO bankAccount, String activation, String description) {

        List<EmployeeEntity> employees = employeeRepository.findEmployeeWithMinimmumRequests();
        ClientEntity clientEntity = clientRepository.findByDNI(client.getDni());
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByIban(bankAccount.getIban()).orElse(null);

        RequestEntity request = new RequestEntity();
        request.setClientByClientId(clientEntity);
        request.setBankAccountByBankAccountId(bankAccountEntity);
        request.setEmployeeByEmployeeId(employees.get(0));
        request.setSolved((byte) 0);
        request.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        request.setType(activation);
        request.setDescription(description == null ? "" : description);

        requestRepository.save(request);
    }
}
