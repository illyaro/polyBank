package com.taw.polybank.controller;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dao.MessageRepository;
import com.taw.polybank.entity.ChatEntity;
import com.taw.polybank.entity.ClientEntity;
import com.taw.polybank.entity.MessageEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("client/assistence")
public class ClientAssistenceController {

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected ChatRepository chatRepository;

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String doListChats(Model model, HttpSession session) {
        ClientEntity client = this.clientRepository.findById((Integer) session.getAttribute("clientId")).orElse(null);
        List<ChatEntity> chatList = (List<ChatEntity>) client.getChatsById();
        model.addAttribute("chatList", chatList);

        return "clientChatList";
    }

    @GetMapping("/chat")
    public String doOpenChat (@RequestParam("id") Integer idChat, Model model) {
        ChatEntity chat = this.chatRepository.findById(idChat).orElse(null);
        model.addAttribute("chat", chat);

        List<MessageEntity> messageList = (List<MessageEntity>) chat.getMessagesById();
        model.addAttribute("messageList", messageList);

        return "clientChat";
    }

    @PostMapping("/newChat")
    public String doNewChat (Model model, HttpSession session) {
        ClientEntity client = this.clientRepository.findById((Integer) session.getAttribute("clientId")).orElse(null);
        ChatEntity chat = new ChatEntity();
        chat.setClientByClientId(client);
        chat.setEmployeeByAssistantId(employeeRepository.);
        chat.setClosed((byte) 0);

        model.addAttribute("chat", chat);
        model.addAttribute("messageList", new ArrayList<MessageEntity>());

        this.chatRepository.save(chat);

        return "clientChat";
    }

    @PostMapping("/send")
    public String doSend (@RequestParam("content") String content, @RequestParam("chatId") Integer chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        MessageEntity message = new MessageEntity();
        message.setChatByChatId(chat);
        message.setContent(content);
        message.setTimestamp(Timestamp.from(Instant.now()));
        message.setEmployeeByEmployeeId(null);
        message.setClientByClientId(chat.getClientByClientId());
        this.messageRepository.save(message);
        return "redirect:/client/assistence/chat?id=" + chatId + "/";
    }

    @PostMapping("/close")
    public String doSend (@RequestParam("chatId") Integer chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        chat.setClosed((byte) 1);
        this.chatRepository.save(chat);
        return "redirect:/client/assistence/";
    }
}
