package com.cs370.springdemo.repository;


import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({FakeMessageRepository.class, MessageService.class })
public class MessageRepositoryUnitTest {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testMessageInsert() {

        entityManager.persist(new Message(1119L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com"));
        var messages = messageRepository.findAll();
        Assertions.assertThat(messageRepository.findAll()).isNotEmpty();

        Assertions.assertThat(messages).extracting(Message::getName).contains("Sergey");
    }
}
