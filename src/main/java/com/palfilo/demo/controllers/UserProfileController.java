package com.palfilo.demo.controllers;

import com.palfilo.demo.DTO.UserProfileDTO;
import com.palfilo.demo.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(userProfileService.getProfileByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<UserProfileDTO> createProfile(@RequestBody UserProfileDTO profileDTO) {
        return ResponseEntity.ok(userProfileService.createProfile(profileDTO));
    }

    @PutMapping
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody UserProfileDTO profileDTO) {
        return ResponseEntity.ok(userProfileService.updateProfile(profileDTO));
    }
}