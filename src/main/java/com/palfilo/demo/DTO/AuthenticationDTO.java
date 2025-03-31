package com.palfilo.demo.DTO;

import java.util.Optional;

public record AuthenticationDTO(
        String email,
        String password,
        String firebaseUUID
) {}
