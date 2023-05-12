package com.cs370.springdemo.service;

import com.cs370.springdemo.model.Customer;
import com.cs370.springdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Customer> getAll() {

        return customerRepository.findAll();
    }

    public Customer getById(String id) {
        return customerRepository.findById(id).orElse(null);
    }


    public Customer insert(Customer customer) {

        return customerRepository.save(customer);
    }


    public Customer update(Customer customer, String id) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        return customerRepository.save(customer);
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
