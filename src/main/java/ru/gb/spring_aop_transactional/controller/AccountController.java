package ru.gb.spring_aop_transactional.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_aop_transactional.dto.TransferRequest;
import ru.gb.spring_aop_transactional.model.Account;
import ru.gb.spring_aop_transactional.service.TransferService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private TransferService transferService;

    @GetMapping
    public List<Account> getAllAccounts(){
        return transferService.getAllAccounts();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
         return transferService.createAccount(account);
    }

    @PostMapping("/transfer")
    public TransferRequest transferMoney(@RequestBody TransferRequest transferRequest){
        TransferRequest transferRequest1 = transferService.transactionalMoney(transferRequest.getSenderAccountId(),
                transferRequest.getReceiveAccountId(), transferRequest.getAmount());
        return transferRequest1;

    }

}
