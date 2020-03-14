package cz.sedy.monitoringservice.repository;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpoint, UUID> {
}
