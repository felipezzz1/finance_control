package com.fezor.finance_control.service;

import com.fezor.finance_control.dto.LoginDTO;
import com.fezor.finance_control.dto.LoginResponseDTO;
import com.fezor.finance_control.dto.RegisterDTO;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    AuthService authService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        authService = new AuthService();

        authService.userRepository = userRepository;
    }

    @Test
    void testRegisterSuccess() {
        RegisterDTO dto = new RegisterDTO();
        dto.username = "Example";
        dto.email = "example@email.com";
        dto.password = "pwexample";

        when(userRepository.findByEmail(dto.email)).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> authService.register(dto));
        verify(userRepository).persist(any(User.class));
    }

    @Test
    void testRegisterDuplicatedEmail() {
        RegisterDTO dto = new RegisterDTO();
        dto.email = "example@email.com";

        when(userRepository.findByEmail(dto.email))
                .thenReturn(new User());

        Assertions.assertThrows(RuntimeException.class, () -> authService.register(dto));
    }

    @Test
    void testLoginSuccess() {
        LoginDTO dto = new LoginDTO();
        dto.email = "example@email.com";
        dto.password = "pwexample";

        User user = new User();
        user.id = 1L;
        user.email = dto.email;
        user.username = "Example";
        user.password = BcryptUtil.bcryptHash("pwexample");

        when(userRepository.findByEmail(dto.email))
                .thenReturn(user);

        LoginResponseDTO response = authService.login(dto);

        Assertions.assertNotNull(response.token);
        Assertions.assertNotNull("Example", response.username);
    }

    @Test
    void testLoginInvalidCredentials() {
        LoginDTO dto = new LoginDTO();
        dto.email = "wrong@email.com";
        dto.password = "pwinvalid";

        when(userRepository.findByEmail(dto.email))
                .thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> authService.login(dto));
    }
}
