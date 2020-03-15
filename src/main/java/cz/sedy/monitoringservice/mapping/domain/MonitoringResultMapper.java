package cz.sedy.monitoringservice.mapping.domain;

import cz.sedy.monitoringservice.config.DomainMappingConfig;
import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoringResultUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = DomainMappingConfig.class)
public interface MonitoringResultMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "monitoredEndpoint", source = "monitoredEndpointId")
    @Mapping(target = "id", ignore = true)
    MonitoringResult mapFromCreateCommand(MonitoringResultCreateCommand createCommand);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "monitoredEndpoint", ignore = true)
    void updateFromUpdateCommand(@MappingTarget MonitoringResult updatedDomain,
                                 MonitoringResultUpdateCommand updateCommand);
}
