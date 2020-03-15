package cz.sedy.monitoringservice.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements IdentifiedDomain {

    @Id
    @GenericGenerator(name = "string-uuid-generator", strategy = "cz.sedy.monitoringservice.generator.StringUUIDGenerator")
    @GeneratedValue(generator = "string-uuid-generator", strategy = GenerationType.SEQUENCE)
    String id;

    String email;

    String username;

    String accessToken;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<MonitoredEndpoint> monitoredEndpoints;
}
