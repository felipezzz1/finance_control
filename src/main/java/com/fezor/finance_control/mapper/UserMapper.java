package com.fezor.finance_control.mapper;

import com.fezor.finance_control.dto.UserResponseDTO;
import com.fezor.finance_control.entity.User;

public class UserMapper {
    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.id, user.username, user.email);
    }
}
