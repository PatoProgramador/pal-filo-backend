package com.palfilo.demo.daos;

import com.palfilo.demo.models.Favorites;
import com.palfilo.demo.models.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
    Optional<Favorites> findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
    @Query(value = """
        SELECT r.*
        FROM core.favorites f
        JOIN core.restaurants r ON f.restaurant_id = r.restaurant_id
        WHERE f.user_id = :userId
    """, nativeQuery = true)
    List<Restaurants> findFavoritesRestaurantsByUserId(@Param("userId") Integer userId);
}
