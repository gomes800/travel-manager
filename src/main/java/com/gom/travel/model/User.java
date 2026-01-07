package com.gom.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String username;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    @Column(nullable = false)
    private LocalDateTime registerDate = LocalDateTime.now();

    @ElementCollection(targetClass = InterestCategory.class)
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "interests")
    private Set<InterestCategory> interests;

    @ElementCollection
    @CollectionTable(name = "user_liked_places", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "place_name")
    private Set<String> likedPlaces = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_disliked_places", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "place_name")
    private Set<String> dislikedPlaces = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_blocked_places", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "place_name")
    private Set<String> blockedPlaces = new HashSet<>();

}
