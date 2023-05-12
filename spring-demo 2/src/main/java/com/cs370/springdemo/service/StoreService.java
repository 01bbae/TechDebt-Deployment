package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Store;
import com.cs370.springdemo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Iterable<Store> getAll() {

        return storeRepository.findAll();
    }

    public Store getById(String id) {
        return storeRepository.findById(id).orElse(null);
    }


    public Store insert(Store store) {

        return storeRepository.save(store);
    }


    public Store update(Store store, String id) {
        if (!storeRepository.existsById(id)) {
            return null;
        }
        return storeRepository.save(store);
    }

    public void delete(String id) {
        storeRepository.deleteById(id);
    }
}
