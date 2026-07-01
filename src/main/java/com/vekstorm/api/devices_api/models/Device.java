package com.vekstorm.api.devices_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
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

    private String sleepMode;

    @Builder.Default
    private List<Object> wakeUpSources = List.of();

    private String batteryLevel;

    @Builder.Default
    private boolean isGateway = false;

    private String gatewayType;
    private String parentGatewayId;
    private String subscriptionId;

    @Builder.Default
    private List<String> scenarioIds = List.of();

    private String userId;

    private Object status;

    @Builder.Default
    private List<Object> ports = List.of();

    @Builder.Default
    private List<Object> actions = List.of();

    @Builder.Default
    private Instant createdAt = Instant.now();
}
