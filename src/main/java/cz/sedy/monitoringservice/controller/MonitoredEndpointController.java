package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.controller.dto.response.MonitoredEndpointResponse;
import cz.sedy.monitoringservice.mapping.response.MonitoredEndpointResponseMapper;
import cz.sedy.monitoringservice.service.MonitoredEndpointService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, value = "/monitored-endpoint")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointController {

    MonitoredEndpointService monitoredEndpointService;
    MonitoredEndpointResponseMapper monitoredEndpointResponseMapper;

    @GetMapping
    public List<MonitoredEndpointResponse> getAllMonitoredEndpoints() {
        return monitoredEndpointService.getAll().stream()
                .map(monitoredEndpointResponseMapper::mapFromDomain)
                .collect(Collectors.toList());
    }

}
