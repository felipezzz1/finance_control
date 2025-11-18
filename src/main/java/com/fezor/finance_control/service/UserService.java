package com.fezor.finance_control.service;

import com.fezor.finance_control.dto.UserCreateDTO;
import com.fezor.finance_control.dto.UserResponseDTO;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    public List<UserResponseDTO> listAll(){
        return User.listAll()
                .stream()
                .map(user -> UserMapper.toDTO((User) user))
                .toList();
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto){
        User user = new User();
        user.username = dto.username;
        user.email = dto.email;
        user.password = dto.password;
        user.persist();

        return UserMapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserCreateDTO dto){
        User user = User.findById(id);
        if (user == null){
            return null;
        }

        user.username = dto.username;
        user.email = dto.email;

        if (dto.password != null){
            user.password = dto.password;
        }

        return UserMapper.toDTO(user);
    }

    @Transactional
    public boolean delete(Long id){
        return User.deleteById(id);
    }

    public User findUserById(Long id){
        return User.findById(id);
    }

}
