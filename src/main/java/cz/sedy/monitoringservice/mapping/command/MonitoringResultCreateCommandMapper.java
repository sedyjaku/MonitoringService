package cz.sedy.monitoringservice.mapping.command;

import cz.sedy.monitoringservice.config.MappingConfig;
import cz.sedy.monitoringservice.controller.dto.request.MonitoringResultRequest;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MonitoringResultCreateCommandMapper {

    MonitoringResultCreateCommand mapFromRequest(MonitoringResultRequest request, UUID monitoredEndpointId);
}
