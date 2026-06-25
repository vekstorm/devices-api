package com.vekstorm.api.devices_api.services;

import com.vekstorm.api.devices_api.models.Device;
import com.vekstorm.api.devices_api.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> findById(UUID id) {
        return deviceRepository.findById(id);
    }

    @Override
    public List<Device> findByUserId(String userId) {
        return deviceRepository.findByUserId(userId);
    }

    @Override
    public List<Device> findByScenarioId(String scenarioId) {
        return deviceRepository.findByScenarioId(scenarioId);
    }

    @Override
    public Device save(Device device) {
        device.setId(UUID.randomUUID());
        device.setCreatedAt(Instant.now());
        return deviceRepository.save(device);
    }

    @Override
    public Device update(UUID id, Device device) {
        return deviceRepository.findById(id)
                .map(existing -> {
                    existing.setName(device.getName());
                    existing.setIp(device.getIp());
                    existing.setRssi(device.getRssi());
                    existing.setStaSsid(device.getStaSsid());
                    existing.setStaPass(device.getStaPass());
                    existing.setDeviceType(device.getDeviceType());
                    existing.setMAC(device.getMAC());
                    existing.setStaEnabled(device.isStaEnabled());
                    existing.setMqttEnabled(device.isMqttEnabled());
                    existing.setWifiConnected(device.isWifiConnected());
                    existing.setMqttConnected(device.isMqttConnected());
                    existing.setMqttServer(device.getMqttServer());
                    existing.setMqttPort(device.getMqttPort());
                    existing.setMqttUser(device.getMqttUser());
                    existing.setMqttPassword(device.getMqttPassword());
                    existing.setWired(device.isWired());
                    existing.setBatteryLevel(device.getBatteryLevel());
                    existing.setGateway(device.isGateway());
                    existing.setGatewayType(device.getGatewayType());
                    existing.setScenarioId(device.getScenarioId());
                    existing.setUserId(device.getUserId());
                    existing.setPorts(device.getPorts());
                    existing.setActions(device.getActions());
                    return deviceRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        deviceRepository.deleteById(id);
    }
}
