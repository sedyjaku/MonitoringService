package cz.sedy.monitoringservice.controller.dto.request;

import java.time.Instant;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointRequest {

    @NotNull
    @Size(max = 50)
    String name;

    @NotNull
    @Size(max = 50)
    String url;

    @NotNull
    Instant createdAt;

    Instant lastCheckedAt;

    @NotNull
    @Min(1)
    Long monitoredInterval;

    @NotNull
    UUID ownerId;
}
