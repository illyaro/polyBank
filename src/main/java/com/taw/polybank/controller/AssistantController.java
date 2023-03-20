package com.taw.polybank.controller;

import com.taw.polybank.dao.ChatRepository;
import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.entity.ChatEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("employee/assistant")
public class AssistantController {

    @Autowired
    protected EmployeeRepository employeeRepository;

    @Autowired
    protected ChatRepository chatRepository;

    @GetMapping("/")
    public String doListChats(Model model, HttpSession session) {
        List<ChatEntity> chatList = this.chatRepository.findAll();
        model.addAttribute("chatList", chatList);

        return "assistantChats";
    }

    @GetMapping("/chat")
    public String doOpenChat (@RequestParam("id") Integer idChat, Model model) {
        ChatEntity chat = this.chatRepository.findById(idChat).orElse(null);

        return "chat";
    }
}
