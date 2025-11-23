package com.fezor.finance_control.util;

import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;

public class UserFactory {

    public static User createUser(String email, String password, UserRepository repository) {
        User user = new User();
        user.username = "Test";
        user.email = email;
        user.password = BcryptUtil.bcryptHash(password);

        repository.persist(user);
        return user;
    }
}
