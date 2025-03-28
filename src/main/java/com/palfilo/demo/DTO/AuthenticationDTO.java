package com.palfilo.demo.DTO;

import java.util.Optional;

public record AuthenticationDTO(
        Optional<String> email,
        Optional<String> password,
        Optional<String> firebaseUUID
) {}
