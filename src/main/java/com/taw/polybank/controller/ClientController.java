package com.taw.polybank.controller;

import com.taw.polybank.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/list")
    public String showClients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clientList";
    }
}
