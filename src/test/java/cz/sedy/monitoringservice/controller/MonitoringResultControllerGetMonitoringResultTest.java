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

public class MonitoringResultControllerGetMonitoringResultTest extends IntegrationTestParent {



    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    public static final UUID MONITORING_RESULT_ID = UUID.fromString("550325f9-2698-4d35-9ac4-1bedb4d1a393");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}";

    @Autowired
    MonitoringResultRepository monitoringResultRepository;

    @Test
    @Sql(scripts = "classpath:/db/monitoring-results/single-monitoring-result.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetMonitoringResult_WithExistingMonitoringResult(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .get(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString(), MONITORING_RESULT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .body("checkedAt", Is.is("2020-03-15T14:01:39Z"))
                .body("payload", Is.is("payload123456"))
                .body("statusCode", Is.is(400))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql(scripts = "classpath:/db/user/single-user.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFound_WithNonExistingMonitoringResult(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .get(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString(), MONITORING_RESULT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
