package cz.sedy.monitoringservice.service;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoringResultUpdateCommand;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;

public interface MonitoringResultService {

    List<MonitoringResult> getAllByMonitoredEndpointId(UUID monitoredEndpointId);

    List<MonitoringResult> search(Specification<MonitoringResult> specification, Long limit);

    MonitoringResult getByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId);

    MonitoringResult create(MonitoringResultCreateCommand createCommand);

    MonitoringResult update(MonitoringResultUpdateCommand updateCommand);

    void deleteByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId);
}
