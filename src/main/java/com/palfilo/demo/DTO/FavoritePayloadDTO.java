package com.palfilo.demo.DTO;

import jakarta.validation.constraints.NotNull;

public record FavoritePayloadDTO(
        @NotNull
        Integer userID,
        @NotNull
        Integer restaurantID
) {}
