package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Customer;
import com.cs370.springdemo.model.CustomerType;
import com.cs370.springdemo.repository.CustomerRepository;
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
public class CustomerServiceUnitTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository repository;

    @Test
    public void testGetAllStores()
    {
//        String id, String firstName, String lastName, CustomerType type, String email,
//                    String accountAddress
        List<Customer> list = new ArrayList<Customer>();
        Customer customerOne = new Customer("1", "fname1", "lname1", CustomerType.registered,"email1@email.com", "addr1");
        Customer customerTwo = new Customer("2", "fname2", "lname2", CustomerType.guest,"email2@email.com", "addr2");
        Customer customerThree = new Customer("3", "fname3", "lname3", CustomerType.registered,"email3@email.com", "addr3");

        list.add(customerOne);
        list.add(customerTwo);
        list.add(customerThree);

        when(repository.findAll()).thenReturn(list);

        Iterable <Customer> empList = customerService.getAll();

        assertEquals(3, ((Collection<Customer>) empList).size());
        verify(repository, times(1)).findAll();
    }
}
