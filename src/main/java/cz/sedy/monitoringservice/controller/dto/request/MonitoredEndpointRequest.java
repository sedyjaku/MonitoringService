package cz.sedy.monitoringservice.controller.dto.request;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointRequest {

    String name;

    String url;

    Instant createdAt;

    Instant lastCheckedAt;

    Long monitoredInterval;

    UUID ownerId;
}
