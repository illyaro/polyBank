package com.taw.polybank.controller;

import com.taw.polybank.controller.company.Client;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.ClientEntity;
import com.taw.polybank.entity.EmployeeEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/view")
    public String viewClient(@RequestParam("id") Integer clientID, Model model) {
        ClientEntity client = this.clientRepository.findById(clientID).orElse(null);
        model.addAttribute("client", client);
        return "clientView";
    }

    @GetMapping("/edit")
    public String editClient(@RequestParam("id") Integer clientID, Model model) {
        ClientEntity client = this.clientRepository.findById(clientID).orElse(null);
        ClientDTO clientDTO = new ClientDTO(client);
        model.addAttribute("client", clientDTO);
        return "clientEdit";
    }

    @GetMapping("/create")
    public String addClient(Model model) {
        ClientEntity client = new ClientEntity();
        model.addAttribute("client", client);
        return "clientAdd";
    }

    @PostMapping("/save")
    public String saveClient (@ModelAttribute("client") ClientDTO clientDTO) {
        ClientEntity client = this.clientRepository.findById(clientDTO.getId()).orElse(null);
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        this.clientRepository.save(client);
        return "redirect:/client/view?id="+client.getId();
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("dni") String dni, @RequestParam("password") String password,
                            HttpSession session)
    {
        ClientEntity client = clientRepository.findByDNI(dni);
        if (client != null) {
            // It should be validated : BCrypt.checkpw(password + employee.getSalt(), employee.getPassword())
            session.setAttribute("clientID", client.getId());
            return "redirect:/client/view?id="+client.getId();
        }
        return ("redirect:/login");
    }
}
