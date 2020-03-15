package cz.sedy.monitoringservice.controller.dto.request;

import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoringResultRequest {

    Instant checkedAt;

    @NotNull
    Integer statusCode;

    @NotNull
    String payload;
}
