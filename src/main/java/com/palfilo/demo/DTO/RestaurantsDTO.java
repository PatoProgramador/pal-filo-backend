package com.palfilo.demo.DTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.palfilo.demo.models.Restaurants;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

public record RestaurantsDTO(
        Integer restaurant_id,
        String name,
        String address,
        CoordinateDTO location,
        String category,
        JsonNode opening_hours,
        String menu_url,
        Timestamp created_at,
        Timestamp updated_at
) {
    public RestaurantsDTO(Restaurants restaurant) {
        this(restaurant.getRestaurantId(), restaurant.getName(),
                restaurant.getAddress(), new CoordinateDTO(restaurant.getLocation()),
                restaurant.getCategory(), restaurant.getOpeningHours(),
                restaurant.getMenuURL(), restaurant.getCreatedAt(),
                restaurant.getUpdatedAt());
    }
}
