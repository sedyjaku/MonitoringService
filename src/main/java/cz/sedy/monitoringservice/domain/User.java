package cz.sedy.monitoringservice.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    UUID id;

    String email;

    String username;

    UUID accessToken;
}
