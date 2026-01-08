package com.gom.travel.model;

import com.gom.travel.dto.TravelRequest;
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
    private Long travelId;

    private String destinyPlace;
    private String destinyResume;
    private int participants;
    private int recommendedBudget;

    @ElementCollection
    @CollectionTable(name = "recommended_activities", joinColumns = @JoinColumn(name = "travel_id"))
    @Column(name = "activities")
    private Set<String> recommendedActivities;

    public Travel(TravelRequest dto) {
        this.destinyPlace = dto.destinyPlace();
        this.destinyResume = dto.destinyResume();
        this.participants = dto.participants();
        this.recommendedBudget = dto.recommendedBudget();
        this.recommendedActivities = dto.recommendedActivities();
    }
}
