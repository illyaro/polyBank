package com.taw.polybank.controller;

import com.taw.polybank.dao.AuthorizedAccountRepository;
import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author José Manuel Sánchez Rico
 */
@Controller
public class LoginController {

    @Autowired
    private AuthorizedAccountRepository authorizedAccountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/")
    public String doShowIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String doLogin()
    {
        return ("login");
    }
}
