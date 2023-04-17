package com.cs370.springdemo.repository;

import com.cs370.springdemo.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Override
    public <S extends Message> S save(S entity);

    @Override
    public <S extends Message> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    public Optional<Message> findById(Long aLong);

    @Override
    public boolean existsById(Long aLong);

    @Override
    public Iterable<Message> findAll();

    @Override
    public Iterable<Message> findAllById(Iterable<Long> longs);

    @Override
    public long count();

    @Override
    public void deleteById(Long aLong);

    @Override
    public void delete(Message entity);

    @Override
    public void deleteAllById(Iterable<? extends Long> longs);

    @Override
    public void deleteAll(Iterable<? extends Message> entities);

    @Override
    public void deleteAll();

}
