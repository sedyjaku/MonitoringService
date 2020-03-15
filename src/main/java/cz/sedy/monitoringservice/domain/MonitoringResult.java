package cz.sedy.monitoringservice.domain;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MonitoringResult implements IdentifiedDomain {

    @Id
    @GenericGenerator(name = "string-uuid-generator", strategy = "cz.sedy.monitoringservice.generator.StringUUIDGenerator")
    @GeneratedValue(generator = "string-uuid-generator", strategy = GenerationType.SEQUENCE)
    String id;

    Instant checkedAt;

    Integer statusCode;

    String payload;

    @ManyToOne
    @JoinColumn(name = "monitored_endpoint_id")
    MonitoredEndpoint monitoredEndpoint;
}
