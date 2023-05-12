package com.cs370.springdemo;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.service.MessageService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "basicAuth")
public class SlowMessageRestController {

    @Autowired
    MessageService messageService;

    @GetMapping("/slowmessages")
    @RateLimiter(name = "getMessageRateLimit", fallbackMethod = "getMessageFallBack")
    public Iterable<Message> getMessageList() {
        return messageService.getAll();
    }

    public ResponseEntity<String> getMessageFallBack(RequestNotPermitted exception) {

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Too many requests : No further request will be accepted. Please try after sometime");
    }

}
