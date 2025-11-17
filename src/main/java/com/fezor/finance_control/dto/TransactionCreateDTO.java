package com.fezor.finance_control.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionCreateDTO {
    public String description;
    public BigDecimal amount;
    public String type;
    public LocalDate date;
    public Long userId;
}
