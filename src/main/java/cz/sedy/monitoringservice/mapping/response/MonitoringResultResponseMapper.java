package cz.sedy.monitoringservice.mapping.response;

import cz.sedy.monitoringservice.config.ResponseMappingConfig;
import cz.sedy.monitoringservice.controller.dto.response.MonitoringResultResponse;
import cz.sedy.monitoringservice.domain.MonitoringResult;
import org.mapstruct.Mapper;

@Mapper(config = ResponseMappingConfig.class)
public interface MonitoringResultResponseMapper {

    MonitoringResultResponse mapFromDomain(MonitoringResult domain);
}
