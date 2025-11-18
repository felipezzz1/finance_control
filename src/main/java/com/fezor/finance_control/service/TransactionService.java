package com.fezor.finance_control.service;

import com.fezor.finance_control.dto.TransactionCreateDTO;
import com.fezor.finance_control.dto.TransactionResponseDTO;
import com.fezor.finance_control.entity.Transaction;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.mapper.TransactionMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class TransactionService {

    @Transactional
    public TransactionResponseDTO create(TransactionCreateDTO dto){

        User user = User.findById(dto.userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        Transaction transaction = new Transaction();
        transaction.description = dto.description;
        transaction.amount = dto.amount;
        transaction.type = dto.type;
        transaction.date = dto.date;
        transaction.user = user;
        transaction.persist();

        return TransactionMapper.toDTO(transaction);
    }

    public List<TransactionResponseDTO> listAll() {
        return Transaction.listAll()
                .stream()
                .map(transaction -> TransactionMapper.toDTO((Transaction) transaction))
                .toList();
    }

    @Transactional
    public TransactionResponseDTO update(Long id, TransactionCreateDTO dto) {
        Transaction transaction = Transaction.findById(id);
        if (transaction == null){
            return null;
        }

        User user = User.findById(dto.userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        transaction.description = dto.description;
        transaction.amount = dto.amount;
        transaction.type = dto.type;
        transaction.date = dto.date;
        transaction.user = user;

        return TransactionMapper.toDTO(transaction);
    }
}
