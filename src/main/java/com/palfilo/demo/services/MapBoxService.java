//package com.palfilo.demo.services;
//
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.palfilo.demo.DTO.RestaurantResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class GooglePlacesService {
//
//    @Value("${google.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();

//    public List<RestaurantResponse> buscarRestaurantes(double lat, double lng, int radius) {
//        String url = String.format(
//                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&type=restaurant&key=%s",
//                lat, lng, radius, apiKey
//        );
//
//        String response = restTemplate.getForObject(url, String.class);
//        JSONPObject jsonResponse = new JSONPObject(response);
//
//        List<RestaurantResponse> restaurantes = new ArrayList<>();
//        JSONArray results = jsonResponse.getJSONArray("results");
//
//        for (int i = 0; i < results.length(); i++) {
//            JSONObject place = results.getJSONObject(i);
//
//            String name = place.getString("name");
//            String address = place.optString("vicinity", "Dirección no disponible");
//            double rating = place.optDouble("rating", 0.0);
//            int userRatings = place.optInt("user_ratings_total", 0);
//
//            JSONObject location = place.getJSONObject("geometry").getJSONObject("location");
//            double latitude = location.getDouble("lat");
//            double longitude = location.getDouble("lng");
//
//            restaurantes.add(new RestaurantResponse(name, address, rating, userRatings, latitude, longitude));
//        }
//
//        return restaurantes;
//    }
//}
