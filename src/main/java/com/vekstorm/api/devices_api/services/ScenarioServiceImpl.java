package com.vekstorm.api.devices_api.services;

import com.vekstorm.api.devices_api.models.Scenario;
import com.vekstorm.api.devices_api.repositories.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private final ScenarioRepository scenarioRepository;

    @Override
    public List<Scenario> findAll() {
        return scenarioRepository.findAll();
    }

    @Override
    public Optional<Scenario> findById(UUID id) {
        return scenarioRepository.findById(id);
    }

    @Override
    public List<Scenario> findByUserId(String userId) {
        return scenarioRepository.findByUserId(userId);
    }

    @Override
    public List<Scenario> findBySubscriptionId(String subscriptionId) {
        return scenarioRepository.findBySubscriptionId(subscriptionId);
    }

    @Override
    public Scenario save(Scenario scenario) {
        scenario.setId(UUID.randomUUID());
        scenario.setCreatedAt(Instant.now());
        return scenarioRepository.save(scenario);
    }

    @Override
    public Scenario update(UUID id, Scenario scenario) {
        return scenarioRepository.findById(id)
                .map(existing -> {
                    existing.setName(scenario.getName());
                    existing.setSubscriptionId(scenario.getSubscriptionId());
                    existing.setUserId(scenario.getUserId());
                    existing.setDescription(scenario.getDescription());
                    existing.setOptions(scenario.getOptions());
                    return scenarioRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Scenario not found with id: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        scenarioRepository.deleteById(id);
    }
}
