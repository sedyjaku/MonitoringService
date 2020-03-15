package cz.sedy.monitoringservice.config;

import cz.sedy.monitoringservice.mapping.UUIDMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring",
        uses = {UUIDMapper.class})
public class ResponseMappingConfig {
}
