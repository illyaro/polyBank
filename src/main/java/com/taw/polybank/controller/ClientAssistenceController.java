package com.taw.polybank.controller;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.ClientRepository;
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

    @GetMapping("/")
    public String doListChats(Model model, HttpSession session) {
        ClientEntity client = this.clientRepository.findById((Integer) session.getAttribute("clientId")).orElse(null);
        List<ChatEntity> chatList = this.chatRepository.searchByClient(client.getId());
        model.addAttribute("chatList", chatList);

        return "clientChatList";
    }

    @GetMapping("/chat")
    public String doOpenChat (@RequestParam("id") Integer idChat, Model model) {
        ChatEntity chat = this.chatRepository.findById(idChat).orElse(null);
        model.addAttribute("chat", chat);

        List<MessageEntity> messageList = this.messageRepository.searchByChat(chat.getId());
        model.addAttribute("messageList", messageList);

        return "assistantChat";
    }

    @PostMapping("/send")
    public String doSend (@RequestParam("content") String content, @RequestParam("chatId") Integer chatId) {
        MessageEntity message = new MessageEntity();
        message.setChatId(chatId);
        message.setContent(content);
        message.setTimestamp(Timestamp.from(Instant.now()));
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
