package cz.sedy.monitoringservice.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements IdentifiedDomain {

    @Id
    @GenericGenerator(name = "string-uuid-generator", strategy = "cz.sedy.monitoringservice.generator.StringUUIDGenerator")
    @GeneratedValue(generator = "string-uuid-generator", strategy = GenerationType.SEQUENCE)
    String id;

    String email;

    String username;

    UUID accessToken;
}
