package cz.sedy.monitoringservice.controller.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonitoredEndpointResponse {

    UUID id;

    String name;

    String url;

    Instant createdAt;

    Instant lastCheckedAt;

    Long monitoredInterval;

    UUID ownerId;
}
