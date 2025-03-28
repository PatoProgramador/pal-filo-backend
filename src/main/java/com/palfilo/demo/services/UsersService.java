package com.palfilo.demo.services;

import com.palfilo.demo.DTO.NewUserDTO;
import com.palfilo.demo.DTO.UserCreatedDTO;
import com.palfilo.demo.daos.LocationPermissionsRepository;
import com.palfilo.demo.daos.UsersRepository;
import com.palfilo.demo.models.LocationPermissions;
import com.palfilo.demo.models.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LocationPermissionsRepository locationPermissionsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserCreatedDTO createUser(NewUserDTO newUser){
        Users user = new Users(newUser);
        if (newUser.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newUser.firebaseUUID()));
        } else user.setPassword(passwordEncoder.encode(newUser.password().get()));
        usersRepository.save(user);

        Integer userId = user.getUserId();

        LocationPermissions locationPermissions = new LocationPermissions(newUser.latitude(), newUser.longitude(), userId);
        locationPermissionsRepository.save(locationPermissions);

        return new UserCreatedDTO(userId);
    }
}
