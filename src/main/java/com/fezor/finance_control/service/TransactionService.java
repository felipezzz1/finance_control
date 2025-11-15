package com.fezor.finance_control.service;

import com.fezor.finance_control.entity.Transaction;
import com.fezor.finance_control.repository.TransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class TransactionService {

    @Inject
    TransactionRepository repository;

    @Transactional
    public Transaction create(Transaction transaction){
        repository.persist(transaction);
        return transaction;
    }

    public List<Transaction> listAll() {
        return repository.listAll();
    }

    public BigDecimal getBalance() {
        BigDecimal income = repository.list("type", "INCOME")
                .stream().map(transaction -> transaction.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outcome = repository.list("type", "OUTCOME")
                .stream().map(transaction -> transaction.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return income.subtract(outcome);
    }
}
