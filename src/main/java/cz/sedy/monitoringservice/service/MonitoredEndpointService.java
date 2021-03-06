package cz.sedy.monitoringservice.service;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointUpdateCommand;
import java.util.UUID;

public interface MonitoredEndpointService {

    MonitoredEndpoint getById(UUID monitoredEndpointId);

    MonitoredEndpoint create(MonitoredEndpointCreateCommand createCommand);

    MonitoredEndpoint update(MonitoredEndpointUpdateCommand updateCommand);

    void deleteById(UUID monitoringResultId);
}
