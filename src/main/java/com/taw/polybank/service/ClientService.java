package com.taw.polybank.service;

import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> findAllClients(){
        List <ClientEntity> clientEntityList= clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (ClientEntity client: clientEntityList) {
            clientDTOList.add(client.toDTO());
        }
        return clientDTOList;
    }

    public ClientDTO autenticar(String user, String password) {
        ClientEntity clientEntity = clientRepository.autenticar(user, password);
        return clientEntity == null ? null : clientEntity.toDTO();
    }

    public void guardarCliente(ClientDTO client, String password) {
        ClientEntity clientEntity = clientRepository.findByDNI(client.getDni());
        clientEntity.setName(client.getName());
        clientEntity.setSurname(client.getSurname());
        clientEntity.setPassword(password);
        clientRepository.save(clientEntity);
    }

    public List<ClientDTO> findByNameOrSurname(String transactionOwner) {
        List<ClientEntity> clientEntityList = clientRepository.findByNameOrSurname(transactionOwner);
        return entityListToDTOList(clientEntityList);
    }

    public List<ClientDTO> entityListToDTOList (List<ClientEntity> clientEntityList){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntityList){
            clientDTOList.add(clientEntity.toDTO());
        }
        return clientDTOList;
    }
}
