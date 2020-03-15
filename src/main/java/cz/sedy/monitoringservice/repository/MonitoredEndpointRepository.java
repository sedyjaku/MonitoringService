package cz.sedy.monitoringservice.repository;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpoint, String> {
}
