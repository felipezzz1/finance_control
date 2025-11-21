package com.fezor.finance_control.service;

import com.fezor.finance_control.dto.LoginDTO;
import com.fezor.finance_control.dto.LoginResponseDTO;
import com.fezor.finance_control.dto.RegisterDTO;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void register(RegisterDTO dto){
        if (userRepository.findByEmail(dto.email) != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.username = dto.username;
        user.email = dto.email;

        user.password = BcryptUtil.bcryptHash(dto.password);

        userRepository.persist(user);
    }

    public LoginResponseDTO login(LoginDTO dto){
        User user = userRepository.findByEmail(dto.email);

        if (user == null || !BcryptUtil.matches(dto.password, user.password)) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = Jwt.issuer("finance-control")
                .subject(user.id.toString())
                .upn(user.email)
                .groups(Set.of("USER"))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .sign();

        return new LoginResponseDTO(token, user.username);
    }

}
