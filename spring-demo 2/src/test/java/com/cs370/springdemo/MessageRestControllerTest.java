package com.cs370.springdemo;

import com.cs370.springdemo.model.Message;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageRestControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers;

    @BeforeAll
    static void init(){

        headers = new HttpHeaders();
        headers.setBasicAuth("sergey", "chapman");
    }

    @Test
    public void testGetMessageById() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\": 1113, \"name\":\"Aaron\", \"lastname\":\"Sundukovskiy\", " +
                "\"email\":\"asunduko@yahoo.com\"}";

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/messages/1113", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testPostMessage() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\": 1117, \"name\":\"Miriam\", \"lastname\":\"Sundukovskiy\", " +
                "\"email\":\"msunduko@yahoo.com\"}";


        Message message = new Message(1117L, "Miriam", "Sundukovskiy","msunduko@yahoo.com");

        HttpEntity<Message> request = new HttpEntity<>(message, headers);

        //ResponseEntity<Message> response = restTemplate.exchange("http://localhost:" + port + "/messages", HttpMethod.POST, request, Message.class);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/messages", request, String.class);

        //response = new TestRestTemplate().exchange(
        //        "http://localhost:" + port + "/messages/1117", HttpMethod.GET, new HttpEntity<String>(headers),
        //        String.class);
        System.out.println(response);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }
}
