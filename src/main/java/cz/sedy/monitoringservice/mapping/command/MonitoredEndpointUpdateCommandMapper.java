package cz.sedy.monitoringservice.mapping.command;

import cz.sedy.monitoringservice.config.MappingConfig;
import cz.sedy.monitoringservice.controller.dto.request.MonitoredEndpointRequest;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointUpdateCommand;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MonitoredEndpointUpdateCommandMapper {

    MonitoredEndpointUpdateCommand mapFromRequest(MonitoredEndpointRequest request, UUID id);
}
