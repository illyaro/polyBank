package com.taw.polybank.controller;

import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.ClientEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/view")
    public String viewClient(Model model, HttpSession session) {
        Integer clientID = (Integer) session.getAttribute("clientID");
        ClientEntity client = this.clientRepository.findById(clientID).orElse(null);
        model.addAttribute("client", client);
        return "client/viewData";
    }

    @GetMapping("/edit")
    public String editClient(Model model, HttpSession session) {
        Integer clientID = (Integer) session.getAttribute("clientID");
        ClientEntity client = this.clientRepository.findById(clientID).orElse(null);
        ClientDTO clientDTO = new ClientDTO(client);
        model.addAttribute("client", clientDTO);
        return "client/editData";
    }

    @GetMapping("/register")
    public String addClient(Model model) {
        ClientEntity client = new ClientEntity();
        model.addAttribute("client", client);
        return "client/register";
    }

    @PostMapping("/save")
    public String saveClient (@ModelAttribute("client") ClientDTO clientDTO, HttpSession session) {
        ClientEntity client = this.clientRepository.findById(clientDTO.getId()).orElse(null);
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        this.clientRepository.save(client);
        return "redirect:/client/view";
    }

    @GetMapping("/account")
    public String viewBankAccount (@RequestParam("id") Integer accountID, Model model, HttpSession session) {
        Integer clientID = (Integer) session.getAttribute("clientID");
        BankAccountEntity account = this.bankAccountRepository.findById(accountID).orElse(null);
        if (account.getClientByClientId().getId() == clientID) {
            model.addAttribute("account", account);
            return "client/bankAccount/viewData";
        } else {
            System.out.println("ERROR: No accesible.");
            return "redirect:/client/view";
        }
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("dni") String dni, @RequestParam("password") String password,
                            HttpSession session) {
        ClientEntity client = clientRepository.findByDNI(dni);
        if (client != null) {
            //BCrypt.checkpw(password + client.getSalt(), client.getPassword());
            session.setAttribute("clientID", client.getId());
            return "redirect:/client/view";
        }
        return ("redirect:/login");
    }
}
