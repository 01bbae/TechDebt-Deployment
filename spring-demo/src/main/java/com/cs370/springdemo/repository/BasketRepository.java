package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, String> {

    @Override
    public <S extends Basket> S save(S entity);

    @Override
    public <S extends Basket> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    public Optional<Basket> findById(String id);

    @Override
    public boolean existsById(String id);

    @Override
    public Iterable<Basket> findAll();

    @Override
    public Iterable<Basket> findAllById(Iterable<String> ids);

    @Override
    public long count();

    @Override
    public void deleteById(String id);

    @Override
    public void delete(Basket entity);

    @Override
    public void deleteAllById(Iterable<? extends String> ids);

    @Override
    public void deleteAll(Iterable<? extends Basket> entities);

    @Override
    public void deleteAll();

}