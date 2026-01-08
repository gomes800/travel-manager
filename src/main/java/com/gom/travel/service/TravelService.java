package com.gom.travel.service;

import com.gom.travel.dto.TravelRequest;
import com.gom.travel.dto.TravelResponse;
import com.gom.travel.model.Travel;
import com.gom.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelResponse saveTravel(TravelRequest dto) {
        Travel travel = new Travel(dto);
        Travel savedTravel = travelRepository.save(travel);
        return TravelResponse.fromEntity(savedTravel);
    }

    public List<TravelResponse> getAllTravels() {
        return travelRepository.findAll()
                .stream()
                .map(TravelResponse::fromEntity)
                .toList();
    }

    public TravelResponse getTravelById(Long id) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel not found with id: " + id));
        return TravelResponse.fromEntity(travel);
    }

    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }
}