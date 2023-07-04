package com.tp1rest.controller;

import com.tp1rest.dto.UserCreateDto;
import com.tp1rest.dto.UserReadDto;
import com.tp1rest.service.UserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class UserControllerMapper {



    @Autowired
    private UserServiceMapper userService;



    @GetMapping("get_user_mapper/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id){


        return new ResponseEntity<>(userService.readUser(id), HttpStatus.OK);

    }




    @PostMapping("created_user_mapper")
    public ResponseEntity<UserReadDto> post(@RequestBody UserCreateDto userCreateDto){

        return new ResponseEntity<UserReadDto>(userService.createUser(userCreateDto), HttpStatus.CREATED);

    }

}