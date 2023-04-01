package com.taw.polybank.controller;

import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.ClientEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/atm")
public class ATMController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/")
    public String doIndex(Model model, HttpSession session) {
        ClientEntity client = (ClientEntity) session.getAttribute("client");
        if (client == null) {
            return "atm/index";
        }else {
            List<BankAccountEntity> bankAccounts = bankAccountRepository.findByClientByClientId(client);
            model.addAttribute("bankAccounts", bankAccounts);
            return "atm/user_data";
        }
    }

    @PostMapping("/login")
    public String doMostrarDatos(@RequestParam("userName") String user, @RequestParam("password") String password, Model model, HttpSession session) {
        ClientEntity client = this.clientRepository.autenticar(user, password);
        if (client == null) {
            model.addAttribute("error", "Credenciales incorrectas");
        } else {
            session.setAttribute("client", client);
        }
        return "redirect:/atm/";
    }

    @GetMapping("/editarDatos")
    public String gotoEditarDatos(Model model, HttpSession session){
        ClientEntity client = (ClientEntity) session.getAttribute("client");
        if (client == null) {
            return "atm/index";
        }else {
            model.addAttribute("client", client);
            return "atm/user_edit";
        }
    }

    @PostMapping("/editarDatos")
    public String doEditarDatos(@ModelAttribute("client") ClientEntity client, Model model, HttpSession session){
        if (session.getAttribute("client") == null)
            return "atm/index";
        clientRepository.save(client);
        session.setAttribute("client", client);
        model.addAttribute("bankAccounts", bankAccountRepository.findByClientByClientId(client));
        return "atm/user_data";
    }

    @PostMapping("/enumerarAcciones")
    public String doListarAcciones(@RequestParam("bankAccount") int bankAccountId, HttpSession session, Model model){
        BankAccountEntity bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        ClientEntity client = (ClientEntity) session.getAttribute("client");
        if (client == null)
            return "atm/index";
        if(bankAccount == null)
            return "atm/user_data";

        model.addAttribute("bankAccount", bankAccount);

        return "atm/bankAccount_actions";
    }

    @PostMapping("/makeTransfer")
    public String doMakeTransfer(@RequestParam("bankAccount") int bankAccountId, HttpSession session, Model model){
        BankAccountEntity bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        return "atm/bankAccount_transferMenu";
    }

}
