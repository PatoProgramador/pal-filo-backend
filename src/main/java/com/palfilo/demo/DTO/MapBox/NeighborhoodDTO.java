package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NeighborhoodDTO(String id, String name) {}
