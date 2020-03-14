package cz.sedy.monitoringservice.domain;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MonitoringResult {

    @Id
    UUID id;

    Instant checkedAt;

    Integer statusCode;

    String payload;

    @ManyToOne
    @JoinColumn(name = "monitored_endpoint_id")
    MonitoredEndpoint monitoredEndpoint;
}
