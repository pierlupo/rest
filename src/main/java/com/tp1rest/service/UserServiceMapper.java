package com.tp1rest.service;

import com.tp1rest.dto.UserCreateDto;
import com.tp1rest.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceMapper {

    public UserCreateDto createUser(UserCreateDto userDto);

    public UserDto readUser(Integer id);



}