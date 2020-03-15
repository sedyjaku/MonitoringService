package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import cz.sedy.monitoringservice.repository.MonitoringResultRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

public class MonitoringResultControllerGetMonitoringResultsTest extends IntegrationTestParent {



    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}/monitoring-results";

    @Autowired
    MonitoringResultRepository monitoringResultRepository;

    @Test
    @Sql(scripts = "classpath:/db/monitoring-results/multiple-monitoring-results-under-different-endpoints.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetMonitoringResults_WithExistingMonitoredEndpoint(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .body("size()", Is.is(13))
                .statusCode(HttpStatus.OK.value());

    }
}
