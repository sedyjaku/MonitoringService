package cz.sedy.monitoringservice.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners({AuditingEntityListener.class})
public class MonitoringResult implements IdentifiedDomain {

    @Id
    @GenericGenerator(name = "string-uuid-generator", strategy = "cz.sedy.monitoringservice.generator.StringUUIDGenerator")
    @GeneratedValue(generator = "string-uuid-generator", strategy = GenerationType.SEQUENCE)
    String id;

    Instant checkedAt;

    Integer statusCode;

    String payload;

    @Column(name = "created_at")
    @CreatedDate
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "monitored_endpoint_id")
    MonitoredEndpoint monitoredEndpoint;
}
