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

public class MonitoringResultSeachControllerSearchTest  extends IntegrationTestParent {

    public static final UUID MONITORED_ENDPOINT_ID = UUID.fromString("b416bed4-bdab-4231-aa49-e7a8ef3ae24c");
    private static final String ENDPOINT_URL = "/monitoring-results";

    @Autowired
    MonitoringResultRepository monitoringResultRepository;

    @Test
    @Sql(scripts = "classpath:/db/monitoring-results/multiple-monitoring-results-under-different-endpoints.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetMonitoringResultsAsc_WithExistingMonitoredEndpoint() {
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .queryParam("url", "test.url-e7a8ef3ae24c")
                .queryParam("sort", "createdAt")
                .queryParam("limit", 10)
                .get(ENDPOINT_URL)
                .then()
                .log()
                .all()
                .assertThat()
                .body("size()", Is.is(10))
                .body("[0].id", Is.is("550325f9-2698-4d35-9ac4-1bedb4d1a393"))
                .body("[1].id", Is.is("c5fbbd5e-8445-4453-a0f5-312bb19a626c"))
                .body("[2].id", Is.is("9dea5fb7-b057-4665-98b3-1cdbde8c4434"))
                .body("[3].id", Is.is("0b3ec18d-2564-493b-93c6-38972dbf604d"))
                .body("[4].id", Is.is("0cee2431-baab-4d04-a0f9-559f4314ab98"))
                .body("[5].id", Is.is("2c70bb63-7a1b-4dec-b525-aaea69b412fd"))
                .body("[6].id", Is.is("d6806c31-77f6-442a-82ca-58956f651c57"))
                .body("[7].id", Is.is("aae51e75-052b-4bc7-898e-accf568bdf6a"))
                .body("[8].id", Is.is("0579ec60-7fd2-48cb-a301-0b2f334f8e68"))
                .body("[9].id", Is.is("415d8731-4d82-4610-95b4-6b2b19c0f38c"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql(scripts = "classpath:/db/monitoring-results/multiple-monitoring-results-under-different-endpoints.sql")
    @Sql(scripts = "classpath:/db/cleanup.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetMonitoringResultsDesc_WithExistingMonitoredEndpoint() {
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .header("access-token", "39548dbf-8129-42eb-881f-645a6d2ed099")
                .queryParam("url", "test.url-e7a8ef3ae24c")
                .queryParam("sort", "-createdAt")
                .queryParam("limit", 10)
                .get(ENDPOINT_URL)
                .then()
                .log()
                .all()
                .assertThat()
                .body("size()", Is.is(10))
                .body("[0].id", Is.is("a4620735-8649-423d-b9b2-5fc0ef063cf5"))
                .body("[1].id", Is.is("a6ec948f-4f27-4629-9f3f-95a0f590cfdf"))
                .body("[2].id", Is.is("564754fb-7092-459a-bf8d-5ef7684ce422"))
                .body("[3].id", Is.is("415d8731-4d82-4610-95b4-6b2b19c0f38c"))
                .body("[4].id", Is.is("0579ec60-7fd2-48cb-a301-0b2f334f8e68"))
                .body("[5].id", Is.is("aae51e75-052b-4bc7-898e-accf568bdf6a"))
                .body("[6].id", Is.is("d6806c31-77f6-442a-82ca-58956f651c57"))
                .body("[7].id", Is.is("2c70bb63-7a1b-4dec-b525-aaea69b412fd"))
                .body("[8].id", Is.is("0cee2431-baab-4d04-a0f9-559f4314ab98"))
                .body("[9].id", Is.is("0b3ec18d-2564-493b-93c6-38972dbf604d"))
                .statusCode(HttpStatus.OK.value());
    }
}