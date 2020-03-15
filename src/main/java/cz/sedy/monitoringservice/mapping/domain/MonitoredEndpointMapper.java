package cz.sedy.monitoringservice.mapping.domain;

import cz.sedy.monitoringservice.config.DomainMappingConfig;
import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = DomainMappingConfig.class)
public interface MonitoredEndpointMapper {

    @Mapping(target = "owner", source = "ownerId")
    @Mapping(target = "monitoringResults", ignore = true)
    @Mapping(target = "id", ignore = true)
    MonitoredEndpoint mapFromCreateCommand(MonitoredEndpointCreateCommand createCommand);

    @Mapping(target = "owner", source = "ownerId")
    @Mapping(target = "monitoringResults", ignore = true)
    void updateFromUpdateCommand(@MappingTarget MonitoredEndpoint updatedDomain,
                                 MonitoredEndpointUpdateCommand updateCommand);
}
