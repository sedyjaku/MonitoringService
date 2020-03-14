package cz.sedy.monitoringservice.controller.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonitoringResultResponse {

    UUID id;

    Instant checkedAt;

    Integer statusCode;

    String payload;
}
