package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.repository.FakeMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {FakeMessageRepository.class, MessageService.class})
public class MessageServiceIntegrationTest {

    @Autowired
    private MessageService messageService;

    @Test
    void insert() {
        Message message = new Message(1112L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com");
        messageService.insert(message);
        assertThat(messageService.getById(1112L).getId()).isEqualTo(1112L);
    }

    @Test
    void update() {
        Message message = new Message(1112L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com");
        messageService.insert(message);

        message.setName("Aaron");
        messageService.update(message, 1112L);
        assertThat(messageService.getById(1112L).getName()).isEqualTo("Aaron");
    }

    @Test
    void delete() {
        Message message = new Message(1112L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com");
        messageService.insert(message);
        messageService.delete(1112L);
        assertThat(messageService.getById(1112L)).isEqualTo(null);
    }

}
