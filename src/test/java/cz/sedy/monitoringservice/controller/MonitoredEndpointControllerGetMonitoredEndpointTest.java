package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

public class MonitoredEndpointControllerGetMonitoredEndpointTest extends IntegrationTestParent {

    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    private static final String ENDPOINT_URL = "/monitored-endpoints/{monitoredEndpointId}";

    @Test
    @Sql(scripts = "classpath:/db/monitored-endpoints/single-monitored-endpoint.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetMonitoredEndpoint_WithExistingEntity(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .get(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .body("name", Is.is("testMonitoredEndpointName"))
                .body("createdAt", Is.is("2020-03-15T14:01:39Z"))
                .body("lastCheckedAt", Is.is("2020-03-15T16:01:43Z"))
                .body("monitoredInterval", Is.is(5))
                .body("url", Is.is("test.url"))
                .body("ownerId", Is.is("6a514e57-ee31-4643-a8c7-0172934cc77b"))
                .statusCode(HttpStatus.OK.value());
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
                .get(ENDPOINT_URL, MONITORED_ENDPOINT_ID.toString())
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
