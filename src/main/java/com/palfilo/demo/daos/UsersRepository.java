package com.palfilo.demo.daos;

import com.palfilo.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByFirebaseUUID(@Param("firebaseUUID") String firebaseUUID);
    Optional<Users> findByEmail(@Param("email") String email);
}
