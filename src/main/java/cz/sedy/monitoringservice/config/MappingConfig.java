package cz.sedy.monitoringservice.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public class MappingConfig {
}
