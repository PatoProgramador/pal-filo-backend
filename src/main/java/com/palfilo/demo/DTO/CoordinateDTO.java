package com.palfilo.demo.DTO;
import org.locationtech.jts.geom.Point;

public record CoordinateDTO(
        Double latitude,
        Double longitude
) {
    public CoordinateDTO(Point point) {
        this(point.getY(), point.getX());
    }
}
