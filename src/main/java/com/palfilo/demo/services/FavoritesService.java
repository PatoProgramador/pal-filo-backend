package com.palfilo.demo.services;

import com.palfilo.demo.DTO.FavoritePayloadDTO;
import com.palfilo.demo.DTO.RestaurantsDTO;
import com.palfilo.demo.daos.FavoritesRepository;
import com.palfilo.demo.models.Favorites;
import com.palfilo.demo.models.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritesService {
    @Autowired
    private FavoritesRepository favoritesRepository;

    public void saveFavoriteRestaurant(FavoritePayloadDTO favoritePayloadDTO) {
        Optional<Favorites> favorites = favoritesRepository.findByUserIdAndRestaurantId(
                favoritePayloadDTO.userID(),
                favoritePayloadDTO.restaurantID()
        );
        if (favorites.isEmpty()) {
            Favorites favorite = new Favorites();
            favorite.setUserId(favoritePayloadDTO.userID());
            favorite.setRestaurantId(favoritePayloadDTO.restaurantID());
            favorite.setAddedAt(new Timestamp(System.currentTimeMillis()));
            favoritesRepository.save(favorite);
        }
    }

    public List<RestaurantsDTO> getUserFavoritesRestaurants(Integer userID) {
        List<Restaurants> restaurants = favoritesRepository.findFavoritesRestaurantsByUserId(userID);
        return restaurants.stream().map(RestaurantsDTO::new).collect(Collectors.toList());
    }

    public void deleteFavoriteRestaurant(FavoritePayloadDTO favoritePayloadDTO) {
        Optional<Favorites> favorites = favoritesRepository.findByUserIdAndRestaurantId(
                favoritePayloadDTO.userID(),
                favoritePayloadDTO.restaurantID()
        );
        favorites.ifPresent(value -> favoritesRepository.delete(value));
    }
}
