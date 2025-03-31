package com.palfilo.demo.controllers;

import com.palfilo.demo.DTO.RestaurantsDTO;
import com.palfilo.demo.services.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantsService restaurantsService;

    @GetMapping("/near/user_id/{userId}")
    public ResponseEntity<List<RestaurantsDTO>> findNearRestaurants(@PathVariable Integer userId) {
        List<RestaurantsDTO> restaurantsDTOList = restaurantsService.getNearRestaurantsByUserId(userId);
        return ResponseEntity.ok(restaurantsDTOList);
    }
}
