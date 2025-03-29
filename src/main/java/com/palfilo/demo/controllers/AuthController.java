package com.palfilo.demo.controllers;

import com.palfilo.demo.DTO.AuthenticationDTO;
import com.palfilo.demo.DTO.NewUserDTO;
import com.palfilo.demo.DTO.TokenDTO;
import com.palfilo.demo.DTO.UserCreatedDTO;
import com.palfilo.demo.services.LoginService;
import com.palfilo.demo.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/users")
    public ResponseEntity<UserCreatedDTO> createUser(@Valid @RequestBody NewUserDTO newUser) {
        return ResponseEntity.ok().body(usersService.createUser(newUser));
    }

    @PostMapping
    public ResponseEntity<TokenDTO> authenticateUser(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok().body(loginService.authenticateUser(authenticationDTO));
    }
}
