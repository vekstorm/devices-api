package com.vekstorm.api.devices_api.services;

import com.vekstorm.api.devices_api.models.Device;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceService {

    List<Device> findAll();

    Optional<Device> findById(UUID id);

    List<Device> findByUserId(String userId);

    List<Device> findByScenarioId(String scenarioId);

    List<Device> findBySubscriptionId(String subscriptionId);

    Device save(Device device);

    Device update(UUID id, Device device);

    void deleteById(UUID id);

    boolean existsByNameAndSubscriptionId(String name, String subscriptionId);
}
