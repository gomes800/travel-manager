package com.gom.travel.dto;

import com.gom.travel.model.Travel;

import java.util.Set;

public record TravelResponse(
        Long travelId,
        String destinyPlace,
        String destinyResume,
        int participants,
        int recommendedBudget,
        Set<String> recommendedActivities

) {
    public static TravelResponse fromEntity(Travel travel){
        return new TravelResponse(
                travel.getTravelId(),
                travel.getDestinyPlace(),
                travel.getDestinyResume(),
                travel.getParticipants(),
                travel.getRecommendedBudget(),
                travel.getRecommendedActivities()
        );
    }
}
