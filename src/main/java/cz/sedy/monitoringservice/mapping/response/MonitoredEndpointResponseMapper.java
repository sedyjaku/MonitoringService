package cz.sedy.monitoringservice.mapping.response;

import cz.sedy.monitoringservice.config.ResponseMappingConfig;
import cz.sedy.monitoringservice.controller.dto.response.MonitoredEndpointResponse;
import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ResponseMappingConfig.class)
public interface MonitoredEndpointResponseMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    MonitoredEndpointResponse mapFromDomain(MonitoredEndpoint domain);

}
