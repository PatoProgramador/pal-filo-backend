package com.palfilo.demo.events;

import com.palfilo.demo.DTO.MapBox.MapBoxResponseDTO;
import com.palfilo.demo.services.MapBoxService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CreateNearRestaurantsListener {
    private final MapBoxService mapBoxService;

    public CreateNearRestaurantsListener(MapBoxService mapBoxService) {
        this.mapBoxService = mapBoxService;
    }

    @EventListener
    public void handleLocationPermissionCreated(CreateNearRestaurantsEvent event) {
        try {
            System.out.println("Buscando restaurantes cerca de: " + event.getLatitude() + ", " + event.getLongitude());
            MapBoxResponseDTO response = mapBoxService.getNearRestaurants(event.getLatitude(), event.getLongitude());
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
