package com.palfilo.demo.DTO.MapBox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenHoursDTO(
        List<PeriodDTO> periods
) {}
