package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.exception.NotFoundException;
import cz.sedy.monitoringservice.mapping.domain.MonitoredEndpointMapper;
import cz.sedy.monitoringservice.repository.MonitoredEndpointRepository;
import cz.sedy.monitoringservice.service.MonitoredEndpointService;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoredEndpointUpdateCommand;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultMonitoredEndpointService implements MonitoredEndpointService {

    MonitoredEndpointRepository monitoredEndpointRepository;
    MonitoredEndpointMapper monitoredEndpointMapper;

    @Override
    public List<MonitoredEndpoint> getAll() {
        return monitoredEndpointRepository.findAll();
    }

    @Override
    public MonitoredEndpoint getById(UUID monitoredEndpointId) {
        return monitoredEndpointRepository.findById(monitoredEndpointId)
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, monitoredEndpointId));
    }

    @Override
    public MonitoredEndpoint create(MonitoredEndpointCreateCommand createCommand) {
        MonitoredEndpoint monitoringResult = monitoredEndpointMapper.mapFromCreateCommand(createCommand);
        return monitoredEndpointRepository.save(monitoringResult);
    }

    @Override
    public MonitoredEndpoint update(MonitoredEndpointUpdateCommand updateCommand) {
        MonitoredEndpoint updatedDomain = monitoredEndpointRepository.findById(updateCommand.getId())
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, updateCommand.getId()));
        monitoredEndpointMapper.updateFromUpdateCommand(updatedDomain, updateCommand);
        return updatedDomain;
    }

    @Override
    public void deleteById(UUID monitoredEndpointId) {
        MonitoredEndpoint deletedDomain = monitoredEndpointRepository.findById(monitoredEndpointId)
                .orElseThrow(() -> NotFoundException.by(MonitoredEndpoint.class, monitoredEndpointId));
        monitoredEndpointRepository.delete(deletedDomain);
    }
}
