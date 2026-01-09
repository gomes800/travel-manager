package com.gom.travel.dto;

import java.util.Set;

public record TravelRequest(
        String destinyPlace,
        String destinyResume,
        Integer participants,
        Integer recommendedBudget,
        Set<String> recommendedActivities
) {
}
