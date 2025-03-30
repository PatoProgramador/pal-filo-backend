package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FeatureDTO(
        String type,
        GeometryDTO geometry,
        PropertiesDTO properties
) {}
