package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import cz.sedy.monitoringservice.controller.dto.request.MonitoringResultRequest;
import cz.sedy.monitoringservice.domain.MonitoringResult;
import cz.sedy.monitoringservice.repository.MonitoringResultRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

public class MonitoringResultControllerCreateMonitoringResultTest extends IntegrationTestParent {

    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}/monitoring-results";

    @Autowired
    MonitoringResultRepository monitoringResultRepository;


    @Test
    @Sql(scripts = "classpath:/db/monitored-endpoints/single-monitored-endpoint.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateMonitoringResult_WithValidRequest() {
        MonitoringResultRequest request = MonitoringResultRequest.builder()
                .checkedAt(Instant.ofEpochSecond(19528L))
                .payload("randomPayload")
                .statusCode(200)
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(request)
                .post(ENDPOINT_URL, MONITORED_ENDPOINT_ID)
                .then()
                .log()
                .all()
                .assertThat()
                .body("checkedAt", Is.is(Instant.ofEpochSecond(19528L).toString()))
                .body("payload", Is.is("randomPayload"))
                .body("statusCode", Is.is(200))
                .statusCode(HttpStatus.OK.value());

        List<MonitoringResult> monitoringResults = monitoringResultRepository.findAll();

        MatcherAssert.assertThat(monitoringResults.size(), Is.is(1));

        MonitoringResult monitoringResult = monitoringResults.get(0);

        MatcherAssert.assertThat(monitoringResult.getCheckedAt(), Is.is(Instant.ofEpochSecond(19528L)));
        MatcherAssert.assertThat(monitoringResult.getPayload(), Is.is("randomPayload"));
        MatcherAssert.assertThat(monitoringResult.getStatusCode(), Is.is(200));
    }


    @Test
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFound_WithNonExistingOwner() {
        MonitoringResultRequest request = MonitoringResultRequest.builder()
                .checkedAt(Instant.ofEpochSecond(19528L))
                .payload("randomPayload")
                .statusCode(200)
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(request)
                .post(ENDPOINT_URL, MONITORED_ENDPOINT_ID)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnBadRequest_WithInvalidRequest() {
        MonitoringResultRequest request = MonitoringResultRequest.builder()
                .checkedAt(Instant.ofEpochSecond(19528L))
                .payload("randomPayload")
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(request)
                .post(ENDPOINT_URL, MONITORED_ENDPOINT_ID)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
