package com.palfilo.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    private String name;
    private String address;
    private double rating;
    private int userRatings;
    private double latitude;
    private double longitude;
}
