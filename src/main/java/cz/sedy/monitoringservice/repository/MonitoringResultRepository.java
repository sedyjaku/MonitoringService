package cz.sedy.monitoringservice.repository;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MonitoringResultRepository extends JpaRepository<MonitoringResult, String>,
        JpaSpecificationExecutor<MonitoringResult> {

    List<MonitoringResult> findAllByMonitoredEndpointId(String monitoredEndpointId);

    Optional<MonitoringResult> findByMonitoredEndpointIdAndId(String monitoredEndpointId, String id);
}
