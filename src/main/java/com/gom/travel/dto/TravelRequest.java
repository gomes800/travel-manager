package com.gom.travel.dto;

import java.util.Set;

public record TravelRequest(
        String destinyPlace,
        String destinyResume,
        int participants,
        int recommendedBudget,
        Set<String> recommendedActivities
) {
}
