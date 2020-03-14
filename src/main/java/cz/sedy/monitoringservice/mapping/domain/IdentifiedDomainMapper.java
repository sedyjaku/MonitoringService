package cz.sedy.monitoringservice.mapping.domain;

import cz.sedy.monitoringservice.domain.IdentifiedDomain;
import cz.sedy.monitoringservice.exception.NotFoundException;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class IdentifiedDomainMapper {

    EntityManager entityManager;

    public <T extends IdentifiedDomain> T getById(@TargetType Class<T> identifiedDomainClass, UUID id) {
        if (id == null) {
            return null;
        }
        T identifiedDomain = entityManager.find(identifiedDomainClass, id);
        if (identifiedDomain == null) {
            throw NotFoundException.by(identifiedDomainClass, id);
        }
        return identifiedDomain;
    }
}
