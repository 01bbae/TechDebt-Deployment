package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Message;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Repository
public class FakeMessageRepository implements MessageRepository {

    private final Map<Long, Message> db = new HashMap<>();

    @Override
    public <S extends Message> S save(S entity) {

        this.db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Message> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> this.db.put(entity.getId(), entity));

        return this.db.values().stream().map(e -> (S)e).collect(Collectors.toList());
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.ofNullable(this.db.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return this.db.containsKey(aLong);
    }

    @Override
    public Iterable<Message> findAll() {
        return this.db.values();
    }

    @Override
    public Iterable<Message> findAllById(Iterable<Long> longs) {

        return this.db.values().stream()
                .filter(entry -> entry.getId().equals(longs.iterator().next()))
                .collect(Collectors.toSet());
    }

    @Override
    public long count() {
        return this.db.size();
    }

    @Override
    public void deleteById(Long aLong) {
        this.db.remove(aLong);

    }

    @Override
    public void delete(Message entity) {
        this.db.entrySet()
                .removeIf(entry -> entry.getValue().equals(entity));
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        longs.forEach(this.db::remove);
    }

    @Override
    public void deleteAll(Iterable<? extends Message> entities) {

        this.db.entrySet()
                .removeIf(entry -> entry.getValue().equals(entities.iterator().next()));

    }

    @Override
    public void deleteAll() {
        this.db.clear();
    }
}