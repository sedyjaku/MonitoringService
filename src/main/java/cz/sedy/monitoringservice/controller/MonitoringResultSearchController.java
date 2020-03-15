package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.controller.dto.response.MonitoringResultResponse;
import cz.sedy.monitoringservice.controller.search.MonitoringResultSort;
import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.mapping.response.MonitoringResultResponseMapper;
import cz.sedy.monitoringservice.service.MonitoringResultService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, value = "/monitoring-results")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Validated
public class MonitoringResultSearchController {

    MonitoringResultService monitoringResultService;
    MonitoringResultResponseMapper monitoringResultResponseMapper;

    @GetMapping
    public List<MonitoringResultResponse> search(
            @And({
                    @Spec(params = "url", path = "monitoredEndpoint.url", spec = Equal.class),
                    @Spec(params = "sort", path = "", spec = MonitoringResultSort.class)
            }) Specification<MonitoringResult> specification,
            @RequestParam Long limit
    ) {
        return monitoringResultService.search(specification, limit).stream()
                .map(monitoringResultResponseMapper::mapFromDomain)
                .collect(Collectors.toList());
    }

}
