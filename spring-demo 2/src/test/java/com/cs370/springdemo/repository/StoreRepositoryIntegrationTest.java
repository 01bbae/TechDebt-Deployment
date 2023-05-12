package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {FakeStoreRepository.class})
public class StoreRepositoryIntegrationTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void testStoreUpdate() {
        Store store = new Store("1", "addr1", "desc1");
        storeRepository.save(store);
        store.setAddress("addr4");
        storeRepository.save(store);
        assertThat(storeRepository.findById("1").get().getAddress()).isEqualTo("addr4");
    }
}
