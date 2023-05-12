package com.cs370.springdemo;

import com.cs370.springdemo.model.Store;
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
public class StoreRestControllerTest {

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

//            Store storeOne = new Store("1", "addr1", "desc1");
//        Store storeTwo = new Store("2", "addr2", "desc2");
//        Store storeThree = new Store("3", "addr3", "desc3");
    @Test
    public void testGetMessageById() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\": \"1\", \"address\":\"addr1\", \"description\":\"desc1\"}";

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/stores/1", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testPostMessage() throws IllegalStateException, JSONException {

        String expectedJson = "{\"id\": \"9\", \"address\":\"addr9\", \"description\":\"desc9\"}";


        Store store = new Store("9", "addr9", "desc9");

        HttpEntity<Store> request = new HttpEntity<>(store, headers);

        //ResponseEntity<Store> response = restTemplate.exchange("http://localhost:" + port + "/messages", HttpMethod.POST, request, Store.class);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/stores", request, String.class);
        System.out.println(response);
        response = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/stores/1", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);
        System.out.println(response);
        System.out.println(response.getBody());
        System.out.println(expectedJson);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }
}
