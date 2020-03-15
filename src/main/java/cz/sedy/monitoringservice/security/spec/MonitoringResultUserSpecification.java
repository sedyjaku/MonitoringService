package cz.sedy.monitoringservice.security.spec;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MonitoringResultUserSpecification implements Specification<MonitoringResult> {

    String ownerId;

    @Override
    public Predicate toPredicate(Root<MonitoringResult> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("monitoredEndpoint").get("owner").get("id"), ownerId);
    }
}
