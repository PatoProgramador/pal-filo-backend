package com.palfilo.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profiles", schema = "auth")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}