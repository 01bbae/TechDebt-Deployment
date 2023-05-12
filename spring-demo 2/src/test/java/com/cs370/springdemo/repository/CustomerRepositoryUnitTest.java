package com.cs370.springdemo.repository;


import com.cs370.springdemo.model.Customer;
import com.cs370.springdemo.model.CustomerType;
import com.cs370.springdemo.service.CustomerService;
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
@Import({FakeCustomerRepository.class, CustomerService.class })
public class CustomerRepositoryUnitTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testMessageInsert() {
//        String id, String firstName, String lastName, CustomerType type, String email,
//                    String accountAddress

        entityManager.persist(new Customer("4", "fname4", "lname4", CustomerType.registered, "email4@email.com", "addr4"));
        var customers = customerRepository.findAll();
        Assertions.assertThat(customerRepository.findAll()).isNotEmpty();
        Assertions.assertThat(customers).extracting(Customer::getFirstName).contains("fname4");
    }
}
