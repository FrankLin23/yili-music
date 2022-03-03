package com.bobby.yilimusic.service;

import com.bobby.yilimusic.dto.UserCreateDto;
import com.bobby.yilimusic.dto.UserDto;
import com.bobby.yilimusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
