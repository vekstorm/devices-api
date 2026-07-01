package com.vekstorm.api.devices_api.controllers;

import com.vekstorm.api.devices_api.models.Device;
import com.vekstorm.api.devices_api.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/devices", produces = "application/json")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    @PreAuthorize("hasAuthority('device:read')")
    public ResponseEntity<List<Device>> getAll(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String scenarioId,
            @RequestParam(required = false) String subscriptionId) {
        if (userId != null) {
            return ResponseEntity.ok(deviceService.findByUserId(userId));
        }
        if (scenarioId != null) {
            return ResponseEntity.ok(deviceService.findByScenarioId(scenarioId));
        }
        if (subscriptionId != null) {
            return ResponseEntity.ok(deviceService.findBySubscriptionId(subscriptionId));
        }
        return ResponseEntity.ok(deviceService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('device:read')")
    public ResponseEntity<Device> getById(@PathVariable UUID id) {
        return deviceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('device:write')")
    public ResponseEntity<Device> create(@RequestBody Device device) {
        Device saved = deviceService.save(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('device:write')")
    public ResponseEntity<Device> update(@PathVariable UUID id, @RequestBody Device device) {
        Device updated = deviceService.update(id, device);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('device:write')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deviceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
