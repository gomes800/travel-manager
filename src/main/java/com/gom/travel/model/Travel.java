package com.gom.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinyPlace;
    private String destinyResume;
    private int participants;
    private int recommendedBudget;

    @ElementCollection
    @CollectionTable(name = "recommended_activities", joinColumns = @JoinColumn(name = "travel_id"))
    @Column(name = "activities")
    private Set<String> recommendedActivities;
}
