package cz.sedy.monitoringservice.controller.dto.request;

import java.time.Instant;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(100)
    @Max(599)
    Integer statusCode;

    @NotNull
    String payload;
}
