package cz.sedy.monitoringservice.controller;

import cz.sedy.monitoringservice.IntegrationTestParent;
import cz.sedy.monitoringservice.controller.dto.request.MonitoredEndpointRequest;
import cz.sedy.monitoringservice.domain.MonitoredEndpoint;
import cz.sedy.monitoringservice.repository.MonitoredEndpointRepository;
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

public class MonitoredEndpointControllerCreateMonitoredEndpointTest extends IntegrationTestParent {

    public static final UUID OWNER_ID = UUID.fromString("6a514e57-ee31-4643-a8c7-0172934cc77b");
    private static final String ENDPOINT_URL = "/monitored-endpoints";

    @Autowired
    MonitoredEndpointRepository monitoredEndpointRepository;

    @Test
    @Sql(scripts = "classpath:/db/user/single-user.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateMonitoredEndpoint_WithValidRequest() {
        MonitoredEndpointRequest request = MonitoredEndpointRequest.builder()
                .createdAt(Instant.ofEpochSecond(52152153L))
                .lastCheckedAt(Instant.ofEpochSecond(9214213L))
                .monitoredInterval(10L)
                .name("Test monitored endpoint name")
                .url("some.random.url")
                .ownerId(OWNER_ID)
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .body(request)
                .post(ENDPOINT_URL)
                .then()
                .log()
                .all()
                .assertThat()
                .body("name", Is.is("Test monitored endpoint name"))
                .body("createdAt", Is.is(Instant.ofEpochSecond(52152153L).toString()))
                .body("lastCheckedAt", Is.is(Instant.ofEpochSecond(9214213L).toString()))
                .body("monitoredInterval", Is.is(10))
                .body("url", Is.is("some.random.url"))
                .body("ownerId", Is.is(OWNER_ID.toString()))
                .statusCode(HttpStatus.OK.value());

        List<MonitoredEndpoint> monitoredEndpoints = monitoredEndpointRepository.findAll();

        MatcherAssert.assertThat(monitoredEndpoints.size(), Is.is(1));

        MonitoredEndpoint monitoredEndpoint = monitoredEndpoints.get(0);

        MatcherAssert.assertThat(monitoredEndpoint.getName(), Is.is("Test monitored endpoint name"));
        MatcherAssert.assertThat(monitoredEndpoint.getCreatedAt(), Is.is(Instant.ofEpochSecond(52152153L)));
        MatcherAssert.assertThat(monitoredEndpoint.getLastCheckedAt(), Is.is(Instant.ofEpochSecond(9214213L)));
        MatcherAssert.assertThat(monitoredEndpoint.getMonitoredInterval(), Is.is(10L));
        MatcherAssert.assertThat(monitoredEndpoint.getUrl(), Is.is("some.random.url"));
        MatcherAssert.assertThat(monitoredEndpoint.getOwner().getId(), Is.is(OWNER_ID.toString()));

    }


    @Test
    @Sql(scripts = "classpath:/db/user/single-user.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFound_WithNonExistingOwner() {
        MonitoredEndpointRequest request = MonitoredEndpointRequest.builder()
                .createdAt(Instant.ofEpochSecond(52152153L))
                .lastCheckedAt(Instant.ofEpochSecond(9214213L))
                .monitoredInterval(10L)
                .name("Test monitored endpoint name")
                .url("some.random.url")
                .ownerId(UUID.fromString("63df919a-c557-4f43-8ad4-ea38f539b391"))
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .body(request)
                .post(ENDPOINT_URL)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Sql(scripts = "classpath:/db/user/single-user.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnBadRequest_WithInvalidRequest() {
        MonitoredEndpointRequest request = MonitoredEndpointRequest.builder()
                .createdAt(Instant.ofEpochSecond(52152153L))
                .lastCheckedAt(Instant.ofEpochSecond(9214213L))
                .monitoredInterval(10L)
                .name("Test monitored endpoint name")
                .ownerId(UUID.fromString("63df919a-c557-4f43-8ad4-ea38f539b391"))
                .build();

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .body(request)
                .post(ENDPOINT_URL)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
