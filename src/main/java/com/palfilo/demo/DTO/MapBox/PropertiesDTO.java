package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PropertiesDTO(
        String name,
        String mapbox_id,
        String feature_type,
        String address,
        String full_address,
        String place_formatted,
        ContextDTO context,
        CoordinatesDTO coordinates,
        String language,
        String maki,
        List<String> poi_category,
        List<String> poi_category_ids,
        ExternalIdsDTO external_ids,
        MetadataDTO metadata,
        Integer distance
) {}
