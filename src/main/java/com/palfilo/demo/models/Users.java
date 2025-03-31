package com.palfilo.demo.models;

import com.palfilo.demo.DTO.NewUserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

import java.sql.Timestamp;

@Table(name = "users", schema = "auth")
@Entity(name = "Account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String email;
    private String password;

    @Column(name = "firebase_uid")
    private String firebaseUUID;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Users(NewUserDTO newUser){
        email = newUser.email();
        firebaseUUID = newUser.firebaseUUID();
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
