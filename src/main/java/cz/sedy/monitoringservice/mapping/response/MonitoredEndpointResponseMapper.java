package cz.sedy.monitoringservice.mapping.response;

import cz.sedy.monitoringservice.config.ResponseMappingConfig;
import cz.sedy.monitoringservice.controller.dto.response.MonitoredEndpointResponse;
import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import org.mapstruct.Mapper;

@Mapper(config = ResponseMappingConfig.class)
public interface MonitoredEndpointResponseMapper {

    MonitoredEndpointResponse mapFromDomain(MonitoredEndpoint domain);

}
