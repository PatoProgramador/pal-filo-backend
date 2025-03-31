package com.palfilo.demo.services;

import com.palfilo.demo.DTO.UserProfileDTO;
import com.palfilo.demo.models.UserProfile;
import com.palfilo.demo.models.Users;
import com.palfilo.demo.daos.UserProfileRepository;
import com.palfilo.demo.daos.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UsersRepository usersRepository;

    public UserProfileDTO getProfileByUserId(Integer userId) {
        return convertToDTO(userProfileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado")));
    }

    public UserProfileDTO createProfile(UserProfileDTO profileDTO) {
        Users user = usersRepository.findById(profileDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (userProfileRepository.findByUser_UserId(profileDTO.getUserId()).isPresent()) {
            throw new RuntimeException("El usuario ya tiene un perfil");
        }

        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setDisplayName(profileDTO.getDisplayName());
        profile.setProfilePhotoUrl(profileDTO.getProfilePhotoUrl());
        profile.setPreferredLanguage(profileDTO.getPreferredLanguage());
        profile.setUpdatedAt(LocalDateTime.now());

        return convertToDTO(userProfileRepository.save(profile));
    }

    public UserProfileDTO updateProfile(UserProfileDTO profileDTO) {
        Users user = usersRepository.findById(profileDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserProfile profile = userProfileRepository.findByUser_UserId(profileDTO.getUserId())
                .orElse(new UserProfile());

        profile.setUser(user);
        profile.setDisplayName(profileDTO.getDisplayName());
        profile.setProfilePhotoUrl(profileDTO.getProfilePhotoUrl());
        profile.setPreferredLanguage(profileDTO.getPreferredLanguage());
        profile.setUpdatedAt(LocalDateTime.now());

        return convertToDTO(userProfileRepository.save(profile));
    }

    private UserProfileDTO convertToDTO(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setProfileId(profile.getProfileId());
        dto.setUserId(profile.getUser().getUserId());
        dto.setDisplayName(profile.getDisplayName());
        dto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        dto.setPreferredLanguage(profile.getPreferredLanguage());
        return dto;
    }
}