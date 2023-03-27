package com.taw.polybank.controller;

import com.taw.polybank.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atm")
public class ATMController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String doMostrarDatos(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "atm/index";
    }

}
