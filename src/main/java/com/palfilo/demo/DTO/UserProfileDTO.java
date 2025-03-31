package com.palfilo.demo.DTO;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Integer profileId;
    private Integer userId;
    private String displayName;
    private String profilePhotoUrl;
    private String preferredLanguage;
}