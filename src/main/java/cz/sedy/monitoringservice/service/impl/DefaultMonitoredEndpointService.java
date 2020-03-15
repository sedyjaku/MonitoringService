package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.exception.NotFoundException;
import cz.sedy.monitoringservice.mapping.domain.MonitoredEndpointMapper;
import cz.sedy.monitoringservice.repository.MonitoredEndpointRepository;
import cz.sedy.monitoringservice.service.MonitoredEndpointService;
import cz.sedy.monitoringservice.service.SecurityService;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointUpdateCommand;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultMonitoredEndpointService implements MonitoredEndpointService {

    MonitoredEndpointRepository monitoredEndpointRepository;
    MonitoredEndpointMapper monitoredEndpointMapper;
    SecurityService securityService;

    @Override
    @Transactional(readOnly = true)
    public MonitoredEndpoint getById(UUID monitoredEndpointId) {
        securityService.checkUserAuthorityOnMonitoredEndpoint(monitoredEndpointId);
        return monitoredEndpointRepository.findById(monitoredEndpointId.toString())
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, monitoredEndpointId));
    }

    @Override
    @Transactional
    public MonitoredEndpoint create(MonitoredEndpointCreateCommand createCommand) {
        MonitoredEndpoint monitoringResult = monitoredEndpointMapper.mapFromCreateCommand(createCommand);
        return monitoredEndpointRepository.save(monitoringResult);
    }

    @Override
    @Transactional
    public MonitoredEndpoint update(MonitoredEndpointUpdateCommand updateCommand) {
        MonitoredEndpoint updatedDomain = monitoredEndpointRepository.findById(updateCommand.getId().toString())
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, updateCommand.getId()));
        monitoredEndpointMapper.updateFromUpdateCommand(updatedDomain, updateCommand);
        return updatedDomain;
    }

    @Override
    @Transactional
    public void deleteById(UUID monitoredEndpointId) {
        securityService.checkUserAuthorityOnMonitoredEndpoint(monitoredEndpointId);
        MonitoredEndpoint deletedDomain = monitoredEndpointRepository.findById(monitoredEndpointId.toString())
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, monitoredEndpointId));
        monitoredEndpointRepository.delete(deletedDomain);
    }
}
