package com.gom.travel.service;

import com.gom.travel.dto.TravelRequest;
import com.gom.travel.dto.TravelResponse;
import com.gom.travel.model.Travel;
import com.gom.travel.model.User;
import com.gom.travel.repository.TravelRepository;
import com.gom.travel.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final AIService aiService;
    private final tools.jackson.databind.ObjectMapper objectMapper;

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

    public TravelResponse createTravelWithIA(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        String prompt = String.format(
                "Gere um JSON de viagem para o perfil: Interesses: %s; Gosta: %s; Odeia: %s; Já visitou: %s. " +
                        "REGRAS: " +
                        "1. destinyResume: Máximo 250 caracteres (seja direto). " +
                        "2. recommendedActivities: Máximo 3 itens. " +
                        "3. Responda APENAS o JSON, sem markdown (```json). " +
                        "MODELO: {\"destinyPlace\": \"\", \"destinyResume\": \"\", \"participants\": 2, \"recommendedBudget\": 0, \"recommendedActivities\": []}",
                user.getInterests(), user.getLikedPlaces(), user.getDislikedPlaces(), user.getVisitedPlaces()
        );

        String rawResponse = aiService.getAIRecommendation(prompt);
        String jsonOnly = rawResponse.substring(rawResponse.indexOf("{"), rawResponse.lastIndexOf("}") + 1);
        TravelRequest dto = objectMapper.readValue(jsonOnly, TravelRequest.class);

        Travel travel = new Travel(dto);
        Travel savedTravel = travelRepository.save(travel);

        user.getTravels().add(savedTravel);
        userRepository.save(user);

        return TravelResponse.fromEntity(savedTravel);
    }
}