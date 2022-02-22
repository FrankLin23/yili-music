package com.bobby.yilimusic.controller;

import com.bobby.yilimusic.mapper.UserMapper;
import com.bobby.yilimusic.service.UserService;
import com.bobby.yilimusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @GetMapping("/")
    List<UserVo> list() {
        return userService.list().stream().map(userMapper::toVo).collect(Collectors.toList());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
