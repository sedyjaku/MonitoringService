package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import cz.sedy.monitoringservice.repository.MonitoringResultRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

public class MonitoringResultControllerDeleteMonitoringResultTest extends IntegrationTestParent {


    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    public static final UUID MONITORING_RESULT_ID = UUID.fromString("550325f9-2698-4d35-9ac4-1bedb4d1a393");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}";

    @Autowired
    MonitoringResultRepository monitoringResultRepository;

    @Test
    @Sql(scripts = "classpath:/db/monitoring-results/single-monitoring-result.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteMonitoringResult_WithExistingEntity(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString(), MONITORING_RESULT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        Assertions.assertFalse(monitoringResultRepository.existsById(MONITORING_RESULT_ID.toString()));
    }

    @Test
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFound_WithNonExistingEntity(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString(), MONITORING_RESULT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
