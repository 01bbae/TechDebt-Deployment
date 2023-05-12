package com.cs370.springdemo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SmartStoreApplication.class)
public class InternalMockMessageRestControllerTest {

    @Test
    void shouldGetMessages() throws JSONException {

        /*String expectedJson = "{\"id\": 11113, \"name\":\"Aaron\", \"lastname\":\"Sundukovskiy\", " +
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

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);*/
    }
}
