package com.taw.polybank.controller;

import com.taw.polybank.controller.company.Client;
import com.taw.polybank.dao.BadgeRepository;
import com.taw.polybank.dao.BankAccountRepository;
import com.taw.polybank.dao.ClientRepository;
import com.taw.polybank.dto.ClientDTO;
import com.taw.polybank.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BadgeRepository badgeRepository;

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

    @GetMapping("/account/moneyExchange")
    public String moneyExchangeOnBankAccount (@RequestParam("id") Integer accountID, Model model, HttpSession session) {
        BadgeEntity badge = new BadgeEntity();
        List<BadgeEntity> badgeList = this.badgeRepository.findAll();
        BankAccountEntity account = this.bankAccountRepository.findById(accountID).orElse(null);
        model.addAttribute("badge", badge);
        model.addAttribute("account", account);
        model.addAttribute("badgeList", badgeList);
        return "client/bankAccount/moneyExchange";
    }
/*
    @PostMapping("/makeExchange")
    public String makeExchange(@ModelAttribute BadgeEntity targetBadge, HttpSession session, Model model) {

        Integer clientID = (Integer) session.getAttribute("clientID");
        Client client = this.clientRepository.findById(clientID).orElse(null);
        BankAccountEntity bankAccount = company.getBankAccountByBankAccountId();
        BadgeEntity currentBadge = bankAccount.getBadgeByBadgeId();
        targetBadge = badgeRepository.findById(targetBadge.getId()).get();
        TransactionEntity transaction = defineTransaction(client, bankAccount);

        BenficiaryEntity beneficiary = beneficiaryRepository.findBenficiaryEntityByNameAndIban(company.getName(), bankAccount.getIban());
        PaymentEntity payment = definePayment(bankAccount.getBalance(), beneficiary, transaction);

        if (beneficiary == null) {
            beneficiary = defineBeneficiary(company.getName(), bankAccount.getIban(), targetBadge, payment);
        } else {
            Collection<PaymentEntity> allPayments = beneficiary.getPaymentsById();
            allPayments.add(payment);
            beneficiary.setPaymentsById(allPayments);
        }
        payment.setBenficiaryByBenficiaryId(beneficiary);

        if (currentBadge.getId() != targetBadge.getId()) {
            CurrencyExchangeEntity currencyExchange = defineCurrencyExchange(currentBadge, targetBadge, bankAccount.getBalance(), transaction, payment);
            payment.setCurrencyExchangeByCurrencyExchangeId(currencyExchange);
            transaction.setCurrencyExchangeByCurrencyExchangeId(currencyExchange);
            transaction.setPaymentByPaymentId(payment);
            updateBadges(currentBadge, targetBadge, currencyExchange);
            currencyExchangeRepository.save(currencyExchange);
            bankAccount.setBalance(currencyExchange.getFinalAmount());
            bankAccount.setBadgeByBadgeId(targetBadge);
            beneficiaryRepository.save(beneficiary);
            paymentRepository.save(payment);
            transactionRepository.save(transaction);
            clientRepository.save(client.getClient());
            bankAccountRepository.save(bankAccount);
            model.addAttribute("message", currencyExchange.getInitialAmount() + " " + currentBadge.getName() + " was successfully exchanged to " + currencyExchange.getFinalAmount() + " " + targetBadge.getName());
        } else {
            model.addAttribute("message", "No exchange was made, chosen currency is actual currency of your bank account.");
        }
        return "/company/userHomepage";
    }
*/
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
