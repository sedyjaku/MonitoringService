package cz.sedy.monitoringservice.controller.dto.request;

import java.time.Instant;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointRequest {

    @NotNull
    String name;

    @NotNull
    String url;

    @NotNull
    Instant createdAt;

    Instant lastCheckedAt;

    @NotNull
    Long monitoredInterval;

    @NotNull
    UUID ownerId;
}
