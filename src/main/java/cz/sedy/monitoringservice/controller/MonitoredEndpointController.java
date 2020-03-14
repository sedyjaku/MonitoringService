package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.controller.dto.request.MonitoredEndpointRequest;
import cz.sedy.monitoringservice.controller.dto.response.MonitoredEndpointResponse;
import cz.sedy.monitoringservice.mapping.command.MonitoredEndpointCreateCommandMapper;
import cz.sedy.monitoringservice.mapping.command.MonitoredEndpointUpdateCommandMapper;
import cz.sedy.monitoringservice.mapping.response.MonitoredEndpointResponseMapper;
import cz.sedy.monitoringservice.service.MonitoredEndpointService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, value = "/monitored-endpoints")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MonitoredEndpointController {

    MonitoredEndpointService monitoredEndpointService;
    MonitoredEndpointResponseMapper monitoredEndpointResponseMapper;
    MonitoredEndpointCreateCommandMapper monitoredEndpointCreateCommandMapper;
    MonitoredEndpointUpdateCommandMapper monitoredEndpointUpdateCommandMapper;

    @GetMapping
    public List<MonitoredEndpointResponse> getAllMonitoredEndpoints() {
        return monitoredEndpointService.getAll().stream()
                .map(monitoredEndpointResponseMapper::mapFromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{monitoredEndpointId}")
    MonitoredEndpointResponse getById(@PathVariable UUID monitoredEndpointId) {
        return monitoredEndpointResponseMapper.mapFromDomain(
                monitoredEndpointService.getById(monitoredEndpointId)
        );
    }

    @PostMapping()
    MonitoredEndpointResponse create(@RequestBody MonitoredEndpointRequest request) {
        return monitoredEndpointResponseMapper.mapFromDomain(
                monitoredEndpointService.create(
                        monitoredEndpointCreateCommandMapper.mapFromRequest(request)
                )
        );
    }

    @PutMapping("/{monitoredEndpointId}")
    MonitoredEndpointResponse update(
            @PathVariable UUID monitoredEndpointId,
            @RequestBody MonitoredEndpointRequest request) {
        return monitoredEndpointResponseMapper.mapFromDomain(
                monitoredEndpointService.create(
                        monitoredEndpointUpdateCommandMapper.mapFromRequest(request, monitoredEndpointId)
                )
        );
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable UUID monitoredEndpointId) {
        monitoredEndpointService.deleteById(monitoredEndpointId);
    }

}
