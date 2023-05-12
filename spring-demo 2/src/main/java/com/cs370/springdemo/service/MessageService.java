package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Iterable<Message> getAll() {

        return messageRepository.findAll();
    }

    public Message getById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }


    public Message insert(Message message) {

        return messageRepository.save(message);
    }


    public Message update(Message message, Long id) {
        if (!messageRepository.existsById(id)) {
            return null;
        }
        return messageRepository.save(message);
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
