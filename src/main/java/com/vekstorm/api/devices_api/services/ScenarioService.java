package com.vekstorm.api.devices_api.services;

import com.vekstorm.api.devices_api.models.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScenarioService {

    List<Scenario> findAll();

    Optional<Scenario> findById(UUID id);

    List<Scenario> findByUserId(String userId);

    List<Scenario> findBySubscriptionId(String subscriptionId);

    Scenario save(Scenario scenario);

    Scenario update(UUID id, Scenario scenario);

    void deleteById(UUID id);
}
