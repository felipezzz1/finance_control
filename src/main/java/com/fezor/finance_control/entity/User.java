package com.fezor.finance_control.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @OneToMany(mappedBy = "user")
    public List<Transaction> transactions;
}
