package com.fezor.finance_control.dto;

public class UserResponseDTO {
    public Long id;
    public String username;
    public String email;

    public UserResponseDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
