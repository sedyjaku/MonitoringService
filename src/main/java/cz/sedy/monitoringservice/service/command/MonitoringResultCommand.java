package cz.sedy.monitoringservice.service.command;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MonitoringResultCommand {

    Instant checkedAt;

    Integer statusCode;

    String payload;

    UUID monitoredEndpointId;
}
