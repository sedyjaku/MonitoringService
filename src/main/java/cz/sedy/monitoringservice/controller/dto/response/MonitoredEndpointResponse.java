package cz.sedy.monitoringservice.controller.dto.response;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Id;

public class MonitoredEndpointResponse {

    @Id
    UUID id;

    String name;

    String url;

    Instant createdAt;

    Instant lastCheckedAt;

    Long monitoredInterval;
}
