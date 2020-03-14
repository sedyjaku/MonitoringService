package cz.sedy.monitoringservice.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements IdentifiedDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String email;

    String username;

    UUID accessToken;
}
