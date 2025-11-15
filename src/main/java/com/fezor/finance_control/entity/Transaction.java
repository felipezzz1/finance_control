package com.fezor.finance_control.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String description;

    @Column(precision = 18, scale = 2, nullable = false)
    public BigDecimal amount;

    @Column(nullable = false)
    public String type;

    @Column(nullable = false)
    public LocalDate date;
}
