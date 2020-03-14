package cz.sedy.monitoringservice.mapping.response;

import cz.sedy.monitoringservice.config.MappingConfig;
import cz.sedy.monitoringservice.controller.dto.response.MonitoredEndpointResponse;
import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MonitoredEndpointResponseMapper {

    MonitoredEndpointResponse mapFromDomain(MonitoredEndpoint domain);

}
