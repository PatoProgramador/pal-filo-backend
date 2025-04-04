package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MetadataDTO(
        String website,
        OpenHoursDTO open_hours
) {}
