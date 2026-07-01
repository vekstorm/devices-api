package com.vekstorm.api.devices_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "scenarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scenario {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    private String name;

    private String subscriptionId;

    private String userId;

    private String description;

    private Object options;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
