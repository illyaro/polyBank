package com.taw.polybank.controller;


import com.taw.polybank.dao.*;
import com.taw.polybank.dto.*;
import com.taw.polybank.entity.*;
import com.taw.polybank.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected BankAccountRepository bankAccountRepository;
    @Autowired
    protected BankAccountService bankAccountService;
    @Autowired
    protected ClientService clientService;
    @Autowired
    protected BadgeService badgeService;
    @Autowired
    protected CurrencyExchangeService currencyExchangeService;
    @Autowired
    protected TransactionService transactionService;
    @Autowired
    protected BeneficiaryService beneficiaryService;
    @Autowired
    protected PaymentService paymentService;

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
        BadgeDTO badge = new BadgeDTO();
        List<BadgeDTO>  badgeList = badgeService.findAll();
        BankAccountEntity account = this.bankAccountRepository.findById(accountID).orElse(null);
        model.addAttribute("badge", badge);
        model.addAttribute("badgeList", badgeList);
        model.addAttribute("account", account);
        return "client/bankAccount/moneyExchange";
    }

    @PostMapping("/account/makeExchange")
    public String makeExchange(@ModelAttribute BadgeDTO targetBadge, @RequestParam("id") Integer accountID, HttpSession session, Model model) {

        Integer clientID = (Integer) session.getAttribute("clientID");
        ClientEntity client = this.clientRepository.findById(clientID).orElse(null);
        ClientDTO clientDTO = new ClientDTO(client);
        BankAccountEntity bankAccount = this.bankAccountRepository.findById(accountID).orElse(null);
        BankAccountDTO accountDTO = new BankAccountDTO(bankAccount);
        BadgeDTO currentBadge = new BadgeDTO(bankAccount.getBadgeByBadgeId());
        targetBadge = badgeService.findById(targetBadge.getId());

        TransactionDTO transaction = defineTransaction(clientDTO, accountDTO);
        BenficiaryDTO beneficiary = defineBeneficiary(client.getName(), bankAccount.getIban(), targetBadge);
        PaymentDTO payment = definePayment(bankAccount.getBalance(), beneficiary);

        payment.setBenficiaryByBenficiaryId(beneficiary);

        if (currentBadge.getId() != targetBadge.getId()) {
            CurrencyExchangeDTO currencyExchange = defineCurrencyExchange(currentBadge, targetBadge, bankAccount.getBalance(), transaction, payment);
            transaction.setPaymentByPaymentId(payment);
            accountDTO.setBalance(currencyExchange.getFinalAmount());
            accountDTO.setBadgeByBadgeId(targetBadge);
            this.beneficiaryService.save(beneficiary);
            paymentService.save(payment, beneficiaryService, currencyExchangeService, badgeService);
            transactionService.save(transaction, clientService, bankAccountService, currencyExchangeService, paymentService, badgeService, beneficiaryService);
            bankAccountService.save(accountDTO, clientService, badgeService);
        } else {
            System.out.println("ERROR: Can't change currency to the same badge.");
        }

        return "redirect:/client/account?id="+accountID;
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

    private PaymentDTO definePayment(Double amount, BenficiaryDTO beneficiary) {
        PaymentDTO payment = new PaymentDTO();
        payment.setAmount(amount);
        payment.setBenficiaryByBenficiaryId(beneficiary);
        return payment;
    }

    private TransactionDTO defineTransaction(ClientDTO client, BankAccountDTO bankAccount) {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setTimestamp(Timestamp.from(Instant.now()));
        transaction.setClientByClientId(client);
        transaction.setBankAccountByBankAccountId(bankAccount);
        return transaction;
    }

    private CurrencyExchangeDTO defineCurrencyExchange(BadgeDTO originBadge, BadgeDTO recipientBadge,
                                                       Double amount, TransactionDTO transaction,
                                                       PaymentDTO payment) {
        CurrencyExchangeDTO currencyExchange = new CurrencyExchangeDTO();
        currencyExchange.setBadgeByInitialBadgeId(originBadge);
        currencyExchange.setBadgeByFinalBadgeId(recipientBadge);
        currencyExchange.setInitialAmount(amount);
        double amountAfterExchange = (recipientBadge.getValue() / originBadge.getValue()) * amount;
        currencyExchange.setFinalAmount(amountAfterExchange);
        payment.setCurrencyExchangeByCurrencyExchangeId(currencyExchange);
        transaction.setCurrencyExchangeByCurrencyExchangeId(currencyExchange);
        currencyExchangeService.save(currencyExchange, badgeService);
        return currencyExchange;
    }

    private BenficiaryDTO defineBeneficiary(String beneficiaryName, String iban, BadgeDTO recipientBadge) {
        BenficiaryDTO beneficiary = new BenficiaryDTO();
        beneficiary.setIban(iban);
        beneficiary.setName(beneficiaryName);
        beneficiary.setBadge(recipientBadge.getName());
        beneficiary.setSwift("XXX" + recipientBadge.getName() + "BNK");
        return beneficiary;
    }
}
