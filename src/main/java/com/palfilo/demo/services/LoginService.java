package com.palfilo.demo.services;

import com.palfilo.demo.DTO.AuthenticationDTO;
import com.palfilo.demo.DTO.TokenDTO;
import com.palfilo.demo.daos.UsersRepository;
import com.palfilo.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import com.palfilo.demo.models.Users;
import com.palfilo.demo.DTO.AuthenticationDTO;
import com.palfilo.demo.DTO.TokenDTO;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsersRepository usersRepository;

    public TokenDTO authenticateUser(AuthenticationDTO authenticationDTO) {
        if (authenticationDTO.email().isPresent() && authenticationDTO.password().isPresent()) {
            Authentication authToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email().get(), authenticationDTO.password().get());
            Authentication userAuth = authenticationManager.authenticate(authToken);

            Users account = (Users) userAuth.getPrincipal();
            String token  = tokenService.generateToken(account);
            return new TokenDTO(account, token);
        } else {
            Optional<Users> user = usersRepository.findByFirebaseUUID(authenticationDTO.firebaseUUID().get());
            if (user.isPresent()) {
                String token  = tokenService.generateToken(user.get());
                return new TokenDTO(user.get(), token);
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        }
    }
}
