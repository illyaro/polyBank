package com.taw.polybank.service;

import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    protected ClientRepository clientRepository;

    public ClientDTO findById(Integer clientId) {
        ClientEntity clientEntity = this.clientRepository.findById(clientId).orElse(null);

        if (clientEntity != null) {
            return clientEntity.toDTO();
        }

        return null;
    }
}
