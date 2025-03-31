package com.palfilo.demo.controllers;

import com.palfilo.demo.DTO.NewUserDTO;
import com.palfilo.demo.DTO.UserCreatedDTO;
import com.palfilo.demo.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private UsersService usersService;


    @PostMapping("/users")
    public ResponseEntity<UserCreatedDTO> createUser(@Valid @RequestBody NewUserDTO newUser) {
        return ResponseEntity.ok().body(usersService.createUser(newUser));
    }
}
