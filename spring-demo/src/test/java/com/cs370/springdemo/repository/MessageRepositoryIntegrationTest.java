package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {FakeMessageRepository.class})
public class MessageRepositoryIntegrationTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testMessageUpdate() {
        Message message = new Message(1112L, "Aaron", "Sundukovskiy","ssunduko@yahoo.com");
        messageRepository.save(message);
        message.setName("Sergey");
        messageRepository.save(message);
        assertThat(messageRepository.findById(1112L).get().getName()).isEqualTo("Sergey");
    }
}
