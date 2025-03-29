package com.palfilo.demo.controllers;

import com.palfilo.demo.daos.UsersRepository;
import com.palfilo.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UsersControllers {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> findAll() throws IOException {
        return ResponseEntity.ok(usersRepository.findAll());
    }
}
