package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceUnitTest {

    @InjectMocks
    MessageService messageService;

    @Mock
    MessageRepository repository;

    @Test
    public void testGetAllMessages()
    {
        List<Message> list = new ArrayList<Message>();
        Message messageOne = new Message(1112L, "Sergey", "Sundukovskiy","ssunduko@yahoo.com");
        Message messageTwo = new Message(1113L, "Aaron", "Sundukovskiy","asunduko@yahoo.com");
        Message messageThree = new Message(1114L, "Rebekah", "Sundukovskiy","rfsunduko@yahoo.com");

        list.add(messageOne);
        list.add(messageTwo);
        list.add(messageThree);

        when(repository.findAll()).thenReturn(list);

        Iterable <Message> empList = messageService.getAll();

        assertEquals(3, ((Collection<Message>) empList).size());
        verify(repository, times(1)).findAll();
    }
}
