package com.taw.polybank.service;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dao.MessageRepository;
import com.taw.polybank.dto.Message;
import com.taw.polybank.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    protected MessageRepository messageRepository;
    @Autowired
    protected ChatRepository chatRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected ClientRepository clientRepository;

    public void save(Message message) {
        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setChatByChatId(chatRepository.findById(message.getChat().getId()).orElse(null));
        messageEntity.setContent(message.getContent());
        messageEntity.setTimestamp(message.getTimestamp());

        if (message.getAssistant() == null) {
            messageEntity.setEmployeeByEmployeeId(null);
        } else {
            messageEntity.setEmployeeByEmployeeId(employeeRepository.findById(message.getAssistant().getId()).orElse(null));
        }

        if (message.getClient() == null) {
            messageEntity.setClientByClientId(null);
        } else {
            messageEntity.setClientByClientId(clientRepository.findById(message.getClient().getId()).orElse(null));
        }

        this.messageRepository.save(messageEntity);
    }
}
