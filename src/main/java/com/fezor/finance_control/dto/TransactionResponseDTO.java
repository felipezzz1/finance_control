package com.fezor.finance_control.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponseDTO {
    public Long id;
    public String description;
    public BigDecimal amount;
    public String type;
    public LocalDate date;

    public Long userId;

    public TransactionResponseDTO(Long id, String description, BigDecimal amount, String type, LocalDate date, Long userId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.userId = userId;
    }
}
