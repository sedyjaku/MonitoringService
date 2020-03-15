package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.controller.dto.request.MonitoringResultRequest;
import cz.sedy.monitoringservice.controller.dto.response.MonitoringResultResponse;
import cz.sedy.monitoringservice.mapping.command.MonitoringResultCreateCommandMapper;
import cz.sedy.monitoringservice.mapping.command.MonitoringResultUpdateCommandMapper;
import cz.sedy.monitoringservice.mapping.response.MonitoringResultResponseMapper;
import cz.sedy.monitoringservice.service.MonitoringResultService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(produces = APPLICATION_JSON_VALUE, value = "/monitored-endpoints/{monitoredEndpointId}/monitoring-results")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Validated
public class MonitoringResultController {

    MonitoringResultService monitoringResultService;
    MonitoringResultResponseMapper monitoringResultResponseMapper;
    MonitoringResultCreateCommandMapper monitoringResultCreateCommandMapper;
    MonitoringResultUpdateCommandMapper monitoringResultUpdateCommandMapper;

    @GetMapping
    public List<MonitoringResultResponse> getAllByMonitoredEndpointId(
            @PathVariable @NotNull UUID monitoredEndpointId
    ) {
        return monitoringResultService.getAllByMonitoredEndpointId(monitoredEndpointId).stream()
                .map(monitoringResultResponseMapper::mapFromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{monitoringResultId}")
    public MonitoringResultResponse getById(
            @PathVariable @NotNull UUID monitoredEndpointId,
            @PathVariable @NotNull UUID monitoringResultId) {
        return monitoringResultResponseMapper.mapFromDomain(
                monitoringResultService.getByIdAndMonitoredEndpointId(monitoringResultId, monitoredEndpointId)
        );
    }

    @PostMapping()
    public MonitoringResultResponse create(
            @PathVariable @NotNull UUID monitoredEndpointId,
            @RequestBody @Valid @NotNull MonitoringResultRequest request) {
        return monitoringResultResponseMapper.mapFromDomain(
                monitoringResultService.create(
                        monitoringResultCreateCommandMapper.mapFromRequest(request, monitoredEndpointId)
                )
        );
    }

    @PutMapping("/{monitoringResultId}")
    public MonitoringResultResponse update(
            @PathVariable @NotNull UUID monitoredEndpointId,
            @PathVariable @NotNull UUID monitoringResultId,
            @RequestBody @Valid @NotNull MonitoringResultRequest request) {
        return monitoringResultResponseMapper.mapFromDomain(
                monitoringResultService.update(
                        monitoringResultUpdateCommandMapper.mapFromRequest(request, monitoredEndpointId, monitoringResultId)
                )
        );
    }

    @DeleteMapping("/{monitoringResultId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @PathVariable @NotNull UUID monitoredEndpointId,
            @PathVariable @NotNull UUID monitoringResultId) {
        monitoringResultService.deleteByIdAndMonitoredEndpointId(monitoringResultId, monitoredEndpointId);
    }

}
