package com.palfilo.demo.daos;

import com.palfilo.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    UserDetails findByEmail(String email);
    Optional<Users> findByFirebaseUUID(String firebaseUUID);
}
