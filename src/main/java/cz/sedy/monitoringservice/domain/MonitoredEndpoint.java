package cz.sedy.monitoringservice.domain;

import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MonitoredEndpoint implements IdentifiedDomain{

    @Id
    @GenericGenerator(name = "string-uuid-generator", strategy = "cz.sedy.monitoringservice.generator.StringUUIDGenerator")
    @GeneratedValue(generator = "string-uuid-generator", strategy = GenerationType.SEQUENCE)
    String id;

    String name;

    String url;

    Instant createdAt;

    Instant lastCheckedAt;

    Long monitoredInterval;

    @ManyToOne
    User owner;

    @OneToMany(mappedBy = "monitoredEndpoint", cascade = CascadeType.REMOVE, orphanRemoval = true)
    Set<MonitoringResult> monitoringResults;
}
