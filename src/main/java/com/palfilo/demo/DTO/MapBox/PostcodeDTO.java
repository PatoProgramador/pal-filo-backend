package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PostcodeDTO(String id, String name) {}
