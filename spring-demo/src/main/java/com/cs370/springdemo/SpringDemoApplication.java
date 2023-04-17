package com.cs370.springdemo;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.repository.FakeMessageRepository;
import com.cs370.springdemo.service.MessageService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(MessageService messageService) {
        return args -> {
            messageService.insert(new Message(1112L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com"));
            messageService.insert(new Message(1113L, "Aaron", "Sundukovskiy","asunduko@yahoo.com"));
            messageService.insert(new Message(1114L, "Rebekah", "Sundukovskiy","rfsunduko@yahoo.com"));
        };
    }

}
