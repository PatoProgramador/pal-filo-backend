package com.palfilo.demo.controllers;

import com.palfilo.demo.DTO.FavoritePayloadDTO;
import com.palfilo.demo.DTO.RestaurantsDTO;
import com.palfilo.demo.services.FavoritesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @PostMapping
    public void saveFavorite(@Valid @RequestBody FavoritePayloadDTO favoritePayloadDTO) {
        favoritesService.saveFavoriteRestaurant(favoritePayloadDTO);
    }

    @GetMapping("/restaurants/user_id/{userId}")
    public ResponseEntity<List<RestaurantsDTO>> getUserFavoritesRestaurants(@PathVariable Integer userId) {
        return ResponseEntity.ok(favoritesService.getUserFavoritesRestaurants(userId));
    }

    @DeleteMapping
    public void deleteFavorite(@Valid @RequestBody FavoritePayloadDTO favoritePayloadDTO) {
        favoritesService.deleteFavoriteRestaurant(favoritePayloadDTO);
    }
}
