package com.palfilo.demo.models;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

@Table(name = "restaurants", schema = "core")
@Entity(name = "Restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    private String name;
    private String address;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    private String category;

    @Column(name = "opening_hours")
    private Object openingHours;

    @Column(name = "menu_url")
    private String menuURL;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
