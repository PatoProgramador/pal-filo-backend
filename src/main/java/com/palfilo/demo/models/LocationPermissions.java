package com.palfilo.demo.models;

import jakarta.persistence.*;
import lombok.*;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;
import java.sql.Timestamp;

@Table(name = "location_permissions", schema = "auth")
@Entity(name = "LocationPermissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LocationPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer permissionId;
    private Boolean allowed;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "granted_at")
    private Timestamp grantedAt;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    public LocationPermissions(Double latitude, Double longitude, Integer userId) {
        GeometryFactory geometryFactory = new GeometryFactory();
        this.location = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        this.userId = userId;
        this.allowed = true;
        this.grantedAt =  new Timestamp(System.currentTimeMillis());
    }
}
