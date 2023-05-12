package com.cs370.springdemo;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SmartStoreApplication.class)
public class ExternalMockMessageRestControllerTest {

    @Test
    void shouldGetMessageById() throws JSONException {

        String expectedJson = "{\"name\":\"name 1\",\"lastname\":\"lastname 1\",\"email\":\"Shirley49@yahoo.com\",\"id\":\"1\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("https://641a33a3c152063412d54b6b.mockapi.io/api/messages/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);
    }
}
