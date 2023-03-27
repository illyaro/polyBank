package com.taw.polybank.controller;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dao.MessageRepository;
import com.taw.polybank.entity.ChatEntity;
import com.taw.polybank.entity.EmployeeEntity;
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
@RequestMapping("employee/assistant")
public class AssistantController {

    @Autowired
    protected EmployeeRepository employeeRepository;

    @Autowired
    protected ChatRepository chatRepository;

    @Autowired
    protected MessageRepository messageRepository;

    @GetMapping("/")
    public String doListChats(Model model, HttpSession session) {
        EmployeeEntity employee = this.employeeRepository.findById((Integer) session.getAttribute("employeeId")).orElse(null);
        List<ChatEntity> chatList = this.chatRepository.searchByAssistant(employee.getId());
        model.addAttribute("chatList", chatList);

        return "assistantChats";
    }

    @GetMapping("/chat")
    public String doOpenChat (@RequestParam("id") Integer idChat, Model model) {
        ChatEntity chat = this.chatRepository.findById(idChat).orElse(null);
        model.addAttribute("chat", chat);

        List<MessageEntity> messageList = this.messageRepository.searchByChat(chat.getId());
        model.addAttribute("messageList", messageList);

        return "chat";
    }

    @PostMapping("/send")
    public String doSend (@RequestParam("content") String content, @RequestParam("chatId") Integer chatId) {
        MessageEntity message = new MessageEntity();
        message.setChatId(chatId);
        message.setContent(content);
        message.setTimestamp(Timestamp.from(Instant.now()));
        this.messageRepository.save(message);
        return "redirect:/employee/assistant/chat";
    }
}
