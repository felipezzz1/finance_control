package com.fezor.finance_control.dto;

public class LoginResponseDTO {
    public String token;
    public String username;

    public LoginResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
