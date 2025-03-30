package com.palfilo.demo.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palfilo.demo.DTO.MapBox.MapBoxResponseDTO;
import com.palfilo.demo.daos.RestaurantRepository;
import com.palfilo.demo.models.Restaurants;
import com.palfilo.demo.services.MapBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Optional;

@Component
public class CreateNearRestaurantsListener {
    private static final Logger logger = LoggerFactory.getLogger(CreateNearRestaurantsListener.class);
    private final MapBoxService mapBoxService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestaurantRepository restaurantRepository;

    public CreateNearRestaurantsListener(MapBoxService mapBoxService) {
        this.mapBoxService = mapBoxService;
    }

    @EventListener
    public void handleLocationPermissionCreated(CreateNearRestaurantsEvent event) {
        try {
            MapBoxResponseDTO response = mapBoxService.getNearRestaurants(event.getLatitude(), event.getLongitude());
            for (int i = 0; i < response.features().size(); i++) {
                Restaurants restaurant = new Restaurants(response.features().get(i));
                Optional<Restaurants> existingRestaurant = restaurantRepository.findByNameAndAddressAndMenuURL(restaurant.getName(), restaurant.getAddress(), restaurant.getMenuURL());
                if (existingRestaurant.isEmpty()) {
                    restaurantRepository.saveRestaurant(restaurant.getAddress(),
                                                        restaurant.getCategory(),
                                                        restaurant.getCreatedAt(),
                                                        restaurant.getLocation(),
                                                        restaurant.getMenuURL(),
                                                        restaurant.getName(),
                                                        objectMapper.writeValueAsString(restaurant.getOpeningHours()),
                                                        restaurant.getUpdatedAt());
                    logger.info("Created new restaurant with name: " + restaurant.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
