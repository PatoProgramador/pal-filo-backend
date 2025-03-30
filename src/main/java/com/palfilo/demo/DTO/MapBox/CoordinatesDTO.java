package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoordinatesDTO(
        Double latitude,
        Double longitude,
        List<RoutablePointDTO> routable_points
) {}
