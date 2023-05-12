package com.cs370.springdemo.repository;


import com.cs370.springdemo.model.Store;
import com.cs370.springdemo.service.StoreService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({FakeStoreRepository.class, StoreService.class })
public class StoreRepositoryUnitTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testMessageInsert() {

        entityManager.persist(new Store("1", "addr1", "desc1"));
        var stores = storeRepository.findAll();
        Assertions.assertThat(storeRepository.findAll()).isNotEmpty();
        Assertions.assertThat(stores).extracting(Store::getAddress).contains("addr1");
    }
}
