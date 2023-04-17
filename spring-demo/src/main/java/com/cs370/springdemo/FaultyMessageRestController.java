package com.cs370.springdemo;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.service.MessageService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@SecurityRequirement(name = "basicAuth")
public class FaultyMessageRestController {

    @Autowired
    MessageService messageService;

    @GetMapping("/faultymessages")
    @Retry(name = "getMessageFault", fallbackMethod = "getMessageFallBack")
    public Iterable<Message> getMessageList() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message> response = restTemplate.getForEntity("http://localhost:" + 1080 + "/messages", Message.class);

        return Arrays.asList(response.getBody());
    }

    public Iterable<Message> getMessageFallBack(Throwable t) {
        return Arrays.asList(new Message(1112L, "Dummy", "Dummy","ssunduko@yahoo.com"));
    }

}
