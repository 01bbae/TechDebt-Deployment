package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Basket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
public class FakeBasketRepository implements BasketRepository {

    private final Map<String, Basket> db = new HashMap<>();

    @Override
    public <S extends Basket> S save(S entity) {

        this.db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Basket> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> this.db.put(entity.getId(), entity));

        return this.db.values().stream().map(e -> (S)e).collect(Collectors.toList());
    }

    @Override
    public Optional<Basket> findById(String id) {
        return Optional.ofNullable(this.db.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return this.db.containsKey(id);
    }

    @Override
    public Iterable<Basket> findAll() {
        return this.db.values();
    }

    @Override
    public Iterable<Basket> findAllById(Iterable<String> ids) {

        return this.db.values().stream()
                .filter(entry -> entry.getId().equals(ids.iterator().next()))
                .collect(Collectors.toSet());
    }

    @Override
    public long count() {
        return this.db.size();
    }

    @Override
    public void deleteById(String id) {
        this.db.remove(id);

    }

    @Override
    public void delete(Basket entity) {
        this.db.entrySet()
                .removeIf(entry -> entry.getValue().equals(entity));
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        ids.forEach(this.db::remove);
    }

    @Override
    public void deleteAll(Iterable<? extends Basket> entities) {

        this.db.entrySet()
                .removeIf(entry -> entry.getValue().equals(entities.iterator().next()));

    }

    @Override
    public void deleteAll() {
        this.db.clear();
    }
}