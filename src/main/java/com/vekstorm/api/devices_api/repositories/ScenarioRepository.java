package com.vekstorm.api.devices_api.repositories;

import com.vekstorm.api.devices_api.models.Scenario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ScenarioRepository extends MongoRepository<Scenario, UUID> {

    List<Scenario> findByUserId(String userId);

    List<Scenario> findBySubscriptionId(String subscriptionId);
}
