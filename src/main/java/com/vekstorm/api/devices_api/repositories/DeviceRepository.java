package com.vekstorm.api.devices_api.repositories;

import com.vekstorm.api.devices_api.models.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends MongoRepository<Device, UUID> {

    List<Device> findByUserId(String userId);

    List<Device> findByScenarioIdsContaining(String scenarioId);

    List<Device> findBySubscriptionId(String subscriptionId);

    boolean existsByNameAndSubscriptionId(String name, String subscriptionId);
}
