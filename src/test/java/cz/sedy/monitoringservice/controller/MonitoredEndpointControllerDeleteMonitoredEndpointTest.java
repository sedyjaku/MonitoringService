package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import cz.sedy.monitoringservice.repository.MonitoredEndpointRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

public class MonitoredEndpointControllerDeleteMonitoredEndpointTest extends IntegrationTestParent {

    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}";

    @Autowired
    MonitoredEndpointRepository monitoredEndpointRepository;

    @Test
    @Sql(scripts = "classpath:/db/monitored-endpoints/single-monitored-endpoint.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteMonitoredEndpoint_WithExistingEntity(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .delete(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        Assertions.assertFalse(monitoredEndpointRepository.existsById(MONITORED_ENDPOINT_ID.toString()));
    }

    @Test
    @Sql(scripts = "classpath:/db/user/single-user.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFound_WithNonExistingEntity(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .delete(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
