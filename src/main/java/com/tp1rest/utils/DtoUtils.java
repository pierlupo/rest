package com.tp1rest.utils;


import com.tp1rest.dto.UserDto;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoUtils {


        public UserDto convertToDto(User user, UserDto userDto) {
            return new ModelMapper().map(user, userDto.getClass());
        }

        public User convertToEntity(User user, UserDto userDto) {
            return new ModelMapper().map(userDto, user.getClass());
        }
    }

