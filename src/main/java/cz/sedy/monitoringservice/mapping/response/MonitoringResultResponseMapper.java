package cz.sedy.monitoringservice.mapping.response;

import cz.sedy.monitoringservice.config.MappingConfig;
import cz.sedy.monitoringservice.controller.dto.response.MonitoringResultResponse;
import cz.sedy.monitoringservice.domain.MonitoringResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MonitoringResultResponseMapper {

    MonitoringResultResponse mapFromDomain(MonitoringResult domain);
}
