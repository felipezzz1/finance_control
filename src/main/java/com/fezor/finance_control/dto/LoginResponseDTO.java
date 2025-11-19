package com.fezor.finance_control.dto;

public class LoginResponseDTO {
    public String token;
    public String name;

    public LoginResponseDTO(String token, String name) {
        this.token = token;
        this.name = name;
    }
}
