package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Store;
import com.cs370.springdemo.repository.StoreRepository;
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
public class StoreServiceUnitTest {

    @InjectMocks
    StoreService storeService;

    @Mock
    StoreRepository repository;

    @Test
    public void testGetAllStores()
    {
        List<Store> list = new ArrayList<Store>();
        Store storeOne = new Store("1", "addr1", "desc1");
        Store storeTwo = new Store("2", "addr2", "desc2");
        Store storeThree = new Store("3", "addr3", "desc3");

        list.add(storeOne);
        list.add(storeTwo);
        list.add(storeThree);

        when(repository.findAll()).thenReturn(list);

        Iterable <Store> empList = storeService.getAll();

        assertEquals(3, ((Collection<Store>) empList).size());
        verify(repository, times(1)).findAll();
    }
}
