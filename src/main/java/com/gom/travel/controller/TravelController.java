package com.gom.travel.controller;

import com.gom.travel.dto.TravelRequest;
import com.gom.travel.dto.TravelResponse;
import com.gom.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @PostMapping
    public ResponseEntity<TravelResponse> create(@RequestBody TravelRequest dto) {
        return ResponseEntity.ok(travelService.saveTravel(dto));
    }

    @GetMapping
    public ResponseEntity<List<TravelResponse>> listAll() {
        return ResponseEntity.ok(travelService.getAllTravels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(travelService.getTravelById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        travelService.deleteTravel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aigen/{userId}")
    public ResponseEntity<TravelResponse> createTravelWithIA(@PathVariable Long userId) {
        return ResponseEntity.ok(travelService.createTravelWithIA(userId));
    }
}