package cz.sedy.monitoringservice.service;

import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.repository.MonitoredEndpointRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointService {

    MonitoredEndpointRepository monitoredEndpointRepository;

    public List<MonitoredEndpoint> getAll(){
        return monitoredEndpointRepository.findAll();
    }
}
