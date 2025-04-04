package ru.gb.spring_aop_transactional.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.spring_aop_transactional.dto.TransferRequest;
import ru.gb.spring_aop_transactional.model.Account;
import ru.gb.spring_aop_transactional.repositories.TransferRepositories;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private TransferRepositories transferRepositories;

    public TransferService(TransferRepositories transferRepositories) {
        this.transferRepositories = transferRepositories;
    }

    @Transactional
    public TransferRequest transactionalMoney(long idSender, long idReceive, BigDecimal amount) {
        Account sender = transferRepositories.findById(idSender).orElseThrow(() -> new RuntimeException("Отправитель не найден!"));
        Account receiver = transferRepositories.findById(idReceive).orElseThrow(() -> new RuntimeException("Получатель не найден!"));

        if (sender.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Недостаточно средств!");
        }
        BigDecimal senderAmount = sender.getAmount().subtract(amount);
        sender.setAmount(senderAmount);
        BigDecimal receiverAmount = receiver.getAmount().add(amount);
        receiver.setAmount(receiverAmount);
        transferRepositories.saveAll(List.of(sender, receiver));

        return new TransferRequest(sender.getId(),receiver.getId(),receiver.getAmount());
    }

    public List<Account> getAllAccounts(){
        return transferRepositories.findAll();
    }

    public Account createAccount(Account account){
     return transferRepositories.save(account);
    }
}
