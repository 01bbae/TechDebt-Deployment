package com.cs370.springdemo;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = SpringDemoApplication.class)
public class BootstrappedMockMessageServerRestControllerTest {

    @BeforeAll
    static void init(){

        ClientAndServer.startClientAndServer(1080);

        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/messages"),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        0
                )
                .respond(
                        response()
                                .withBody("{\n  \"id\" : 11113,\n  \"name\" : \"Aaron\",\n  \"lastname\" : \"Sundukovskiy\",\n  \"email\" : \"aasunduko@yahoo.com\"\n}")
                );
    }
    @Test
    void shouldGetMessages() throws JSONException {

        String expectedJson = "{\"id\": 11113, \"name\":\"Aaron\", \"lastname\":\"Sundukovskiy\", " +
                "\"email\":\"aasunduko@yahoo.com\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:1080/messages")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);
    }
}
