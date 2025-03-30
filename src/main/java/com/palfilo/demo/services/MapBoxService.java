package com.palfilo.demo.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palfilo.demo.DTO.MapBox.MapBoxResponseDTO;
import okhttp3.OkHttpClient;import okhttp3.Request;import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MapBoxService {
    @Value("${MAP_BOX_API_KEY}")
    private String MAPBOX_API_KEY;

    private final ObjectMapper objectMapper = new ObjectMapper();

    final String MAPBOX_API_URL = "https://api.mapbox.com/search/searchbox/v1/category/restaurant?";

    public MapBoxResponseDTO getNearRestaurants(double latitude, double longitude) throws IOException {
        int limit = 10;
        String bbox = (longitude - 0.05) + "," + (latitude - 0.05) + "," +
                (longitude + 0.05) + "," + (latitude + 0.05);

        String url = MAPBOX_API_URL +
                "proximity=" + longitude + "," + latitude +
                "&bbox=" + bbox +
                "&limit=" + limit +
                "&access_token=" + MAPBOX_API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful() && response.body() != null) {
            return objectMapper.readValue(response.body().string(), MapBoxResponseDTO.class);
        } else {
            throw new IOException("Error al obtener los restaurantes: " + response.message());
        }
    }
}
