package com.palfilo.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "favorites", schema = "core")
@Entity(name = "Favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private int favoriteId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "added_at")
    private Timestamp addedAt;
}
