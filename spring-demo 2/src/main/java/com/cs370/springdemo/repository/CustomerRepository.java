package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Override
    public <S extends Customer> S save(S entity);

    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    public Optional<Customer> findById(String id);

    @Override
    public boolean existsById(String id);

    @Override
    public Iterable<Customer> findAll();

    @Override
    public Iterable<Customer> findAllById(Iterable<String> ids);

    @Override
    public long count();

    @Override
    public void deleteById(String id);

    @Override
    public void delete(Customer entity);

    @Override
    public void deleteAllById(Iterable<? extends String> ids);

    @Override
    public void deleteAll(Iterable<? extends Customer> entities);

    @Override
    public void deleteAll();

}