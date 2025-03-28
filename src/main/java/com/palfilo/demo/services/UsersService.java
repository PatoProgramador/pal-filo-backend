package com.palfilo.demo.services;

import com.palfilo.demo.DTO.NewUserDTO;
import com.palfilo.demo.DTO.UserCreatedDTO;
import com.palfilo.demo.daos.UsersRepository;
import com.palfilo.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCreatedDTO createUser(NewUserDTO newUser){
        Users user = new Users(newUser);
        if (newUser.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newUser.firebaseUUID()));
        } else user.setPassword(passwordEncoder.encode(newUser.password().get()));
        usersRepository.save(user);

        return new UserCreatedDTO(user.getUserId());
    }
}
