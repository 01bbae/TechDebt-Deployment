package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Store;
import com.cs370.springdemo.repository.FakeStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {FakeStoreRepository.class, StoreService.class})
public class StoreServiceIntegrationTest {

    @Autowired
    private StoreService storeService;

    @Test
    void insert() {
        Store store = new Store("1", "addr1", "desc1");
        storeService.insert(store);
        assertThat(storeService.getById("1").getId()).isEqualTo("1");
    }

    @Test
    void update() {
        Store store = new Store("2", "addr2", "desc2");
        storeService.insert(store);

        store.setAddress("addr4");
        storeService.update(store, "2");
        assertThat(storeService.getById("2").getAddress()).isEqualTo("addr4");
    }

    @Test
    void delete() {
        Store store = new Store("2", "addr2", "desc2");
        storeService.insert(store);
        storeService.delete("2");
        assertThat(storeService.getById("2")).isEqualTo(null);
    }

}
