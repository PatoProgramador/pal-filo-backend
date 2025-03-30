package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContextDTO(
        CountryDTO country,
        RegionDTO region,
        PostcodeDTO postcode,
        PlaceDTO place,
        LocalityDTO locality,
        NeighborhoodDTO neighborhood,
        StreetDTO street
) {}
