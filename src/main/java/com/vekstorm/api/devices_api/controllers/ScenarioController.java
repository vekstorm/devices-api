package com.vekstorm.api.devices_api.controllers;

import com.vekstorm.api.devices_api.models.Scenario;
import com.vekstorm.api.devices_api.services.ScenarioService;
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
@RequestMapping(value = "/api/v1/scenarios", produces = "application/json")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('scenario:read')")
    public ResponseEntity<List<Scenario>> getAll(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String subscriptionId) {
        if (userId != null) {
            return ResponseEntity.ok(scenarioService.findByUserId(userId));
        }
        if (subscriptionId != null) {
            return ResponseEntity.ok(scenarioService.findBySubscriptionId(subscriptionId));
        }
        return ResponseEntity.ok(scenarioService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('scenario:read')")
    public ResponseEntity<Scenario> getById(@PathVariable UUID id) {
        return scenarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('scenario:write')")
    public ResponseEntity<Scenario> create(@RequestBody Scenario scenario) {
        Scenario saved = scenarioService.save(scenario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('scenario:write')")
    public ResponseEntity<Scenario> update(@PathVariable UUID id, @RequestBody Scenario scenario) {
        Scenario updated = scenarioService.update(id, scenario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('scenario:write')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        scenarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
