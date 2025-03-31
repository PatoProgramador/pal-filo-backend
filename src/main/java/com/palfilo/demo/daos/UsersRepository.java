package com.palfilo.demo.daos;

import com.palfilo.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByFirebaseUUID(String firebaseUUID);
}
