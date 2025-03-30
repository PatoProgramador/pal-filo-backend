package com.palfilo.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palfilo.demo.DTO.MapBox.FeatureDTO;
import com.palfilo.demo.utils.JsonNodeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
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

    @Column(name = "opening_hours", columnDefinition = "jsonb")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode openingHours;

    @Column(name = "menu_url")
    private String menuURL;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Restaurants(FeatureDTO feature) {
        GeometryFactory geometryFactory = new GeometryFactory();

        this.name = feature.properties().name();
        this.address = feature.properties().full_address();
        this.location = geometryFactory.createPoint(new Coordinate(feature.properties().coordinates().longitude(), feature.properties().coordinates().latitude()));
        this.category = feature.properties().poi_category().getFirst();
        this.menuURL = feature.properties().metadata().website();
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        this.openingHours = new ObjectMapper().valueToTree(feature.properties().metadata().open_hours());
    }
}
