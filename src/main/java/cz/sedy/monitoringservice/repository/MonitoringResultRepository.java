package cz.sedy.monitoringservice.repository;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringResultRepository extends JpaRepository<MonitoringResult, String> {

    List<MonitoringResult> findAllByMonitoredEndpointId(String monitoredEndpointId);

    Optional<MonitoringResult> findByMonitoredEndpointIdAndId(String monitoredEndpointId, String id);
}
