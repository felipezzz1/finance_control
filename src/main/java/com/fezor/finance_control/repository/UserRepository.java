package com.fezor.finance_control.repository;

import com.fezor.finance_control.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UserRepository implements PanacheRepository<User> {

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
