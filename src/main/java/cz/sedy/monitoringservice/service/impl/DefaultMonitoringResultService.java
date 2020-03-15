package cz.sedy.monitoringservice.service.impl;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.domain.User;
import cz.sedy.monitoringservice.exception.NotFoundException;
import cz.sedy.monitoringservice.mapping.domain.MonitoringResultMapper;
import cz.sedy.monitoringservice.repository.MonitoringResultRepository;
import cz.sedy.monitoringservice.security.spec.MonitoringResultUserSpecification;
import cz.sedy.monitoringservice.service.MonitoringResultService;
import cz.sedy.monitoringservice.service.SecurityService;
import cz.sedy.monitoringservice.service.command.MonitoringResultCreateCommand;
import cz.sedy.monitoringservice.service.command.MonitoringResultUpdateCommand;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultMonitoringResultService implements MonitoringResultService {

    MonitoringResultRepository monitoringResultRepository;
    MonitoringResultMapper monitoringResultMapper;
    SecurityService securityService;

    @Override
    @Transactional(readOnly = true)
    public List<MonitoringResult> getAllByMonitoredEndpointId(UUID monitoredEndpointId) {
        securityService.checkUserAuthorityOnMonitoredEndpoint(monitoredEndpointId);
        return monitoringResultRepository.findAllByMonitoredEndpointId(monitoredEndpointId.toString());
    }

    @Override
    public List<MonitoringResult> search(Specification<MonitoringResult> specification, Long limit) {
        Specification<MonitoringResult> specificationWithUser = addUserSpecification(specification);
        return monitoringResultRepository.findAll(specificationWithUser, PageRequest.of(0, limit.intValue())).getContent();
    }


    @Override
    @Transactional(readOnly = true)
    public MonitoringResult getByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId) {
        securityService.checkUserAuthorityOnMonitoredEndpoint(monitoredEndpointId);
        return monitoringResultRepository.findByMonitoredEndpointIdAndId(
                monitoredEndpointId.toString(), monitoringResultId.toString())
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, monitoringResultId));
    }

    @Override
    @Transactional
    public MonitoringResult create(MonitoringResultCreateCommand createCommand) {
        MonitoringResult monitoringResult = monitoringResultMapper.mapFromCreateCommand(createCommand);
        return monitoringResultRepository.save(monitoringResult);
    }

    @Override
    @Transactional
    public MonitoringResult update(MonitoringResultUpdateCommand updateCommand) {
        MonitoringResult updatedDomain = monitoringResultRepository.findByMonitoredEndpointIdAndId(
                updateCommand.getMonitoredEndpointId().toString(), updateCommand.getId().toString())
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, updateCommand.getId()));
        monitoringResultMapper.updateFromUpdateCommand(updatedDomain, updateCommand);
        return updatedDomain;
    }

    @Override
    @Transactional
    public void deleteByIdAndMonitoredEndpointId(UUID monitoringResultId, UUID monitoredEndpointId) {
        MonitoringResult deletedDomain = monitoringResultRepository.findByMonitoredEndpointIdAndId(
                monitoredEndpointId.toString(), monitoringResultId.toString())
                .orElseThrow(() -> NotFoundException.by(MonitoringResult.class, monitoringResultId)
                );
        monitoringResultRepository.delete(deletedDomain);
    }

    private Specification<MonitoringResult> addUserSpecification(Specification<MonitoringResult> specification) {
        User user = securityService.getUser();
        return specification.and(new MonitoringResultUserSpecification(user.getId()));
    }

}
