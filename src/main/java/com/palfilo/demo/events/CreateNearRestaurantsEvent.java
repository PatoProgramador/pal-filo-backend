package com.palfilo.demo.events;
import org.springframework.context.ApplicationEvent;

public class CreateNearRestaurantsEvent extends ApplicationEvent {
    private final double latitude;
    private final double longitude;

    public CreateNearRestaurantsEvent(Object source, double latitude, double longitude) {
        super(source);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
