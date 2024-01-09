package com.axa.axatest.controller;


import com.axa.axatest.dto.RequestUserDto;
import com.axa.axatest.dto.ResponseUserDto;
import com.axa.axatest.entity.User;
import com.axa.axatest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class  UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAllUser(){
        //List<ResponseUserDto> responseDto = new ArrayList<>();
        try{
            var responseUserDto = userService.findAllUser().stream()
                    .map(user -> new ResponseUserDto(user.getUserId(),user.getUserName()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RequestUserDto requestUserDto) {
        try {
            User user = userService.create(new User(requestUserDto.userId(),requestUserDto.userName(),requestUserDto.password()));
            var responseUserDto = new ResponseUserDto(user.getUserId(),user.getUserName());
            return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id){
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()){
            var responseUserDto = new ResponseUserDto(user.get().getUserId(),user.get().getUserName());
            return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
