package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.exception.NotFoundException;
import cz.sedy.monitoringservice.mapping.domain.MonitoringResultMapper;
import cz.sedy.monitoringservice.repository.MonitoringResultRepository;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoringResultUpdateCommand;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultMonitoringResultService implements cz.sedy.monitoringservice.service.MonitoringResultService {

    MonitoringResultRepository monitoringResultRepository;
    MonitoringResultMapper monitoringResultMapper;

    @Override
    public List<MonitoringResult> getAllByMonitoredEndpointId(UUID monitoredEndpointId) {
        return monitoringResultRepository.findAll();
    }

    @Override
    public MonitoringResult getByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId) {
        return monitoringResultRepository.findById(monitoringResultId)
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, monitoringResultId));
    }

    @Override
    public MonitoringResult create(MonitoringResultCreateCommand createCommand) {
        MonitoringResult monitoringResult = monitoringResultMapper.mapFromCreateCommand(createCommand);
        return monitoringResultRepository.save(monitoringResult);
    }

    @Override
    public MonitoringResult update(MonitoringResultUpdateCommand updateCommand) {
        MonitoringResult updatedDomain = monitoringResultRepository.findById(updateCommand.getId())
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, updateCommand.getId()));
        monitoringResultMapper.updateFromUpdateCommand(updatedDomain, updateCommand);
        return updatedDomain;
    }

    @Override
    public void deleteByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId) {
        MonitoringResult deletedDomain = monitoringResultRepository.findById(monitoringResultId)
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, monitoringResultId)
                );
        monitoringResultRepository.delete(deletedDomain);
    }

}
