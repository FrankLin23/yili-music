package com.bobby.yilimusic.service.impl;

import com.bobby.yilimusic.dto.UserCreateDto;
import com.bobby.yilimusic.dto.UserDto;
import com.bobby.yilimusic.entity.User;
import com.bobby.yilimusic.exception.BizException;
import com.bobby.yilimusic.exception.ExceptionType;
import com.bobby.yilimusic.mapper.UserMapper;
import com.bobby.yilimusic.repository.UserRepository;
import com.bobby.yilimusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repository;

    UserMapper mapper;

    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        return repository.findAll().stream().map(mapper ::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUserName(userCreateDto.getUsername());
        User user = mapper.createEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(user));
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private void checkUserName(String username) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }
}
