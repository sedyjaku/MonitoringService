package cz.sedy.monitoringservice.domain;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MonitoredEndpoint {

    @Id
    UUID id;

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
