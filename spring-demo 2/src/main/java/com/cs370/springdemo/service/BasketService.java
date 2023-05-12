package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Basket;
import com.cs370.springdemo.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    BasketRepository basketRepository;

    public Iterable<Basket> getAll() {

        return basketRepository.findAll();
    }

    public Basket getById(String id) {
        return basketRepository.findById(id).orElse(null);
    }


    public Basket insert(Basket basket) {

        return basketRepository.save(basket);
    }


    public Basket update(Basket basket, String id) {
        if (!basketRepository.existsById(id)) {
            return null;
        }
        return basketRepository.save(basket);
    }

    public void delete(String id) {
        basketRepository.deleteById(id);
    }
}
