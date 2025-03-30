package com.palfilo.demo.DTO.MapBox;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MapBoxResponseDTO(
        String type,
        List<FeatureDTO> features
) {}
