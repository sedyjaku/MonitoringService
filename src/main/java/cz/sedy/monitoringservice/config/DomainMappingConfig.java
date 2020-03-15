package cz.sedy.monitoringservice.config;

import cz.sedy.monitoringservice.mapping.StringMapper;
import cz.sedy.monitoringservice.mapping.domain.IdentifiedDomainMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring",
        uses = {IdentifiedDomainMapper.class, StringMapper.class})
public class DomainMappingConfig {
}
