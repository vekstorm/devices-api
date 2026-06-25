package com.vekstorm.api.devices_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Document(collection = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    private String name;

    private String ip;

    private String rssi;

    private String staSsid;

    private String staPass;

    private String deviceType;

    private String MAC;

    @Builder.Default
    private boolean staEnabled = false;

    @Builder.Default
    private boolean mqttEnabled = false;

    @Builder.Default
    private boolean wifiConnected = false;

    @Builder.Default
    private boolean mqttConnected = false;

    private String mqttServer;

    private String mqttPort;

    private String mqttUser;

    private String mqttPassword;

    @Builder.Default
    private boolean wired = false;

    private String batteryLevel;

    @Builder.Default
    private boolean isGateway = false;

    private String gatewayType;

    private String scenarioId;

    private String userId;

    private Map<String, Object> ports;

    private Map<String, Object> actions;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
