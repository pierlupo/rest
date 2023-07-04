package com.tp1rest.service;

import com.tp1rest.dto.UserCreateDto;
import com.tp1rest.dto.UserDto;

public interface UserServiceMapper {

    public UserCreateDto createUser(UserCreateDto userDto);

    public UserDto readUser(Integer id);



}