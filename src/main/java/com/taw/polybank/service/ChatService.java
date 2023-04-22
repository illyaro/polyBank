package com.taw.polybank.service;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dto.Chat;
import com.taw.polybank.dto.Employee;
import com.taw.polybank.entity.ChatEntity;
import com.taw.polybank.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    protected ChatRepository chatRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected ClientRepository clientRepository;

    public Chat findById(Integer chatId) {
        ChatEntity chatEntity = this.chatRepository.findById(chatId).orElse(null);

        if (chatEntity != null) {
            return chatEntity.toDTO();
        }

        return null;
    }

    public List<Chat> findByEmployeeAndClientDni(Employee employee, String clientDni) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientDni(employeeEntity, clientDni);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndClientDniAndClientName(Employee employee, String clientDni, String clientName) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientDniAndClientName(employeeEntity, clientDni, clientName);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndClientDniAndRecent(Employee employee, String clientDni) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientDniAndRecent(employeeEntity, clientDni);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndClientDniAndClientNameAndRecent(Employee employee, String clientDni, String clientName) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientDniAndClientNameAndRecent(employeeEntity, clientDni, clientName);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndClientName(Employee employee, String clientName) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientName(employeeEntity, clientName);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndClientNameAndRecent(Employee employee, String clientName) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndClientNameAndRecent(employeeEntity, clientName);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public List<Chat> findByEmployeeAndRecent(Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId()).orElse(null);
        List<Chat> chatList = new ArrayList<>();

        if (employeeEntity != null) {
            List<ChatEntity> chatEntityList = chatRepository.findByEmployeeAndRecent(employeeEntity);
            chatList = this.listToDTO(chatEntityList);
        }

        return chatList;
    }

    public void save(Chat chat) {
        ChatEntity chatEntity = new ChatEntity();

        chatEntity.setClientByClientId(clientRepository.findById(chat.getClient().getId()).orElse(null));
        chatEntity.setEmployeeByAssistantId(employeeRepository.findById(chat.getAssistant().getId()).orElse(null));
        chatEntity.setMessagesById(new ArrayList<>());
        chatEntity.setClosed(chat.getClosed());

        this.chatRepository.save(chatEntity);
    }

    public void close(Chat chat) {
        ChatEntity chatEntity = chatRepository.findById(chat.getId()).orElse(null);

        if (chat != null) {
            chatEntity.setClosed(chat.getClosed());

            chatRepository.save(chatEntity);
        }
    }

    protected List<Chat> listToDTO(List<ChatEntity> chatEntityList) {
        ArrayList chatList = new ArrayList<Chat>();
        chatEntityList.forEach((final ChatEntity chatEntity) -> chatList.add(chatEntity.toDTO()));

        return chatList;
    }
}
