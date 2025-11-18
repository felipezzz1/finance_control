package com.fezor.finance_control.mapper;

import com.fezor.finance_control.dto.TransactionResponseDTO;
import com.fezor.finance_control.entity.Transaction;

public class TransactionMapper {
    public static TransactionResponseDTO toDTO(Transaction transaction){
        return new TransactionResponseDTO(
                transaction.id,
                transaction.description,
                transaction.amount,
                transaction.type,
                transaction.date,
                transaction.user != null ? transaction.user.id : null
        );
    }
}
