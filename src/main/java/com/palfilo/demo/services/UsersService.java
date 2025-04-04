package com.palfilo.demo.services;

import com.palfilo.demo.DTO.AuthenticationDTO;
import com.palfilo.demo.DTO.NewUserDTO;
import com.palfilo.demo.DTO.UserCreatedDTO;
import com.palfilo.demo.daos.LocationPermissionsRepository;
import com.palfilo.demo.daos.UsersRepository;
import com.palfilo.demo.events.CreateNearRestaurantsEvent;
import com.palfilo.demo.models.LocationPermissions;
import com.palfilo.demo.models.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LocationPermissionsRepository locationPermissionsRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public UserCreatedDTO createUser(NewUserDTO newUser){
        Users user = new Users(newUser);
        if (newUser.password().isEmpty()) {
            user.setPassword(newUser.firebaseUUID());
        } else user.setPassword(newUser.password().get());
        usersRepository.save(user);

        Integer userId = user.getUserId();

        LocationPermissions locationPermissions = new LocationPermissions(newUser.latitude(), newUser.longitude(), userId);
        locationPermissionsRepository.save(locationPermissions);

        eventPublisher.publishEvent(new CreateNearRestaurantsEvent(this, newUser.latitude(), newUser.longitude()));

        return new UserCreatedDTO(userId);
    }

    public UserCreatedDTO getUserById(AuthenticationDTO authenticationDTO) {
        Integer userId = 0;
        if (authenticationDTO.email() != null && authenticationDTO.password() != null) {
            Optional<Users> user = usersRepository.findByEmail(authenticationDTO.email());
            if (user.isPresent()) {
                System.out.println(user.get().getUserId());
                userId = user.get().getUserId();
            }
        } else if (authenticationDTO.firebaseUUID() != null) {
            Optional<Users> user = usersRepository.findByFirebaseUUID(authenticationDTO.firebaseUUID());
            if (user.isPresent()) {
                userId = user.get().getUserId();
            }
        }
        return new UserCreatedDTO(userId);
    }
}
