package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Customer;
import com.cs370.springdemo.model.CustomerType;
import com.cs370.springdemo.repository.FakeCustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {FakeCustomerRepository.class, CustomerService.class})
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void insert() {
        Customer customer = new Customer("1", "fname1", "lname1", CustomerType.registered,"email1@email.com", "addr1");
        customerService.insert(customer);
        assertThat(customerService.getById("1").getId()).isEqualTo("1");
    }

    @Test
    void update() {
        Customer customer = new Customer("2", "fname2", "lname2", CustomerType.guest,"email2@email.com", "addr2");
        customerService.insert(customer);

        customer.setAccountAddress("addr4");
        customerService.update(customer, "2");
        assertThat(customerService.getById("2").getAccountAddress()).isEqualTo("addr4");
    }

    @Test
    void delete() {
        Customer customer = new Customer("2", "fname2", "lname2", CustomerType.guest,"email2@email.com", "addr2");
        customerService.insert(customer);
        customerService.delete("2");
        assertThat(customerService.getById("2")).isEqualTo(null);
    }

}
