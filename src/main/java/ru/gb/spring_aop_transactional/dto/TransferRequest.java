package ru.gb.spring_aop_transactional.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private long senderAccountId;
    private long receiveAccountId;
    private BigDecimal amount;

    public TransferRequest(long senderAccountId, long receiveAccountId, BigDecimal amount) {
        this.senderAccountId = senderAccountId;
        this.receiveAccountId = receiveAccountId;
        this.amount = amount;
    }
}
