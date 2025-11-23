package com.fezor.finance_control.resource;

import com.fezor.finance_control.dto.LoginDTO;
import com.fezor.finance_control.dto.RegisterDTO;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.repository.UserRepository;
import com.fezor.finance_control.util.UserFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AuthResourceTest {

    @Inject
    UserRepository userRepository;

    @Test
    void testRegister() {
        RegisterDTO dto = new RegisterDTO();
        dto.username = "Example User";
        dto.email = "example@user.com";
        dto.password = "123456";

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(201);
    }

    @Test
    void testLogin() {
        User user = UserFactory.createUser("login_test@test.com", "a1b2c3", userRepository);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.email = user.email;
        loginDTO.password = "a1b2c3";

        var response = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Assertions.assertTrue(response.contains("token"));
    }
}
