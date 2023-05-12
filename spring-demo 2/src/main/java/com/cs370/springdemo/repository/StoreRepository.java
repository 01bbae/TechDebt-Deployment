package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends CrudRepository<Store, String> {

    @Override
    public <S extends Store> S save(S entity);

    @Override
    public <S extends Store> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    public Optional<Store> findById(String id);

    @Override
    public boolean existsById(String id);

    @Override
    public Iterable<Store> findAll();

    @Override
    public Iterable<Store> findAllById(Iterable<String> ids);

    @Override
    public long count();

    @Override
    public void deleteById(String id);

    @Override
    public void delete(Store entity);

    @Override
    public void deleteAllById(Iterable<? extends String> ids);

    @Override
    public void deleteAll(Iterable<? extends Store> entities);

    @Override
    public void deleteAll();

}