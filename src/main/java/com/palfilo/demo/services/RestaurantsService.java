package com.palfilo.demo.services;

import com.palfilo.demo.DTO.RestaurantsDTO;
import com.palfilo.demo.daos.RestaurantRepository;
import com.palfilo.demo.models.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantsService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantsDTO> getNearRestaurantsByUserId(Integer userId) {
        List<Restaurants> restaurants = restaurantRepository.findNearbyRestaurants(userId);
        Restaurants restaurants1 = restaurants.get(0);
        System.out.println(restaurants1.getLocation().getCoordinate());
        return restaurants.stream().map(RestaurantsDTO::new).collect(Collectors.toList());
    }
}
