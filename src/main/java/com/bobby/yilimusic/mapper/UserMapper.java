package com.bobby.yilimusic.mapper;

import com.bobby.yilimusic.dto.UserCreateDto;
import com.bobby.yilimusic.dto.UserDto;
import com.bobby.yilimusic.entity.User;
import com.bobby.yilimusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateDto userCreateDto);
}
