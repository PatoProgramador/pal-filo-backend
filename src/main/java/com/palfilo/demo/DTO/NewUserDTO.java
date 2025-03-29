package com.palfilo.demo.DTO;

import java.util.Optional;

public record NewUserDTO (
        String email,
        Optional<String> password,
        String firebaseUUID,
        Double latitude,
        Double longitude
){}
