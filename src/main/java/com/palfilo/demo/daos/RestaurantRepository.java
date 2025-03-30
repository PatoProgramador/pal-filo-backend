package com.palfilo.demo.daos;

import com.palfilo.demo.models.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.locationtech.jts.geom.Point;

public interface RestaurantRepository extends JpaRepository<Restaurants, Integer> {
    Optional<Restaurants> findByNameAndAddressAndMenuURL(String name, String address, String menuURL);
    @Modifying
    @Query(value = "INSERT INTO core.restaurants (address, category, created_at, location, menu_url, name, opening_hours, updated_at) " +
            "VALUES (:address, :category, :createdAt, :location, :menuUrl, :name, CAST(:openingHours AS jsonb), :updatedAt)",
            nativeQuery = true)
    void saveRestaurant(@Param("address") String address,
                        @Param("category") String category,
                        @Param("createdAt") Timestamp createdAt,
                        @Param("location")  Point location,
                        @Param("menuUrl") String menuUrl,
                        @Param("name") String name,
                        @Param("openingHours") String openingHours,
                        @Param("updatedAt") Timestamp updatedAt);

    @Query(value = """
        SELECT r.*, ST_Distance(r.location::geography, lp.location::geography) AS distance
        FROM core.restaurants r
        JOIN auth.location_permissions lp ON lp.user_id = :userId
        WHERE ST_DWithin(r.location::geography, lp.location::geography, 5000)
        ORDER BY distance ASC
    """, nativeQuery = true)
    List<Restaurants> findNearbyRestaurants(@Param("userId") Integer userId);
}
