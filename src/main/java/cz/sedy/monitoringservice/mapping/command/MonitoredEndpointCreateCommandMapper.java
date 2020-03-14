package cz.sedy.monitoringservice.mapping.command;

import cz.sedy.monitoringservice.config.MappingConfig;
import cz.sedy.monitoringservice.controller.dto.request.MonitoredEndpointRequest;
import cz.sedy.monitoringservice.controller.dto.request.MonitoringResultRequest;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import java.util.UUID;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MonitoredEndpointCreateCommandMapper {

    MonitoredEndpointCreateCommand mapFromRequest(MonitoredEndpointRequest request);
}
