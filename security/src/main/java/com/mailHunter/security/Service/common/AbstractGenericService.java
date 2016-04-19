package com.mailHunter.security.Service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Transactional
public abstract class AbstractGenericService<T extends Persistable<ID>, ID extends Serializable,
        REPO extends JpaRepository<T, ID>> implements com.mailHunter.security.Service.common.GenericService<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericService.class);

    @SuppressWarnings({"unchecked"})
    protected final String simpleTypeName = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0]).getSimpleName();

    @Autowired
    protected REPO repository;

    public long count() {
        return repository.count();
    }

    public void delete(ID id) {
        LOGGER.info("Deleting {} with id {}", simpleTypeName, id);
        repository.delete(id);
    }

    public void delete(Iterable<? extends T> entities) {
        LOGGER.info("Deleting {} sequence", simpleTypeName);
        repository.delete(entities);
    }

    public void delete(T entity) {
        LOGGER.info("Deleting {}", entity);
        repository.delete(entity);
    }

    public void deleteAll() {
        LOGGER.info("Deleting all {} entities", simpleTypeName);
        repository.deleteAll();
    }

    public void deleteAllInBatch() {
        LOGGER.info("Deleting all {} entities in batch", simpleTypeName);
        repository.deleteAllInBatch();
    }

    public void deleteInBatch(Iterable<T> entities) {
        LOGGER.info("Deleting all {} entities in batch", simpleTypeName);
        repository.deleteInBatch(entities);
    }

    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public List<T> findAll() {
        LOGGER.debug("Finding all {} entities", simpleTypeName);
        List<T> found = repository.findAll();
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    public Iterable<T> findAll(Iterable<ID> ids) {
        LOGGER.debug("Finding all {} entities with ids {}", simpleTypeName, ids);
        Iterable<T> found = repository.findAll(ids);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    public Page<T> findAll(Pageable pageable) {
        LOGGER.debug("Finding all {} entities with pageable restrictions {}", simpleTypeName, pageable);
        Page<T> found = repository.findAll(pageable);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    public List<T> findAll(Sort sort) {
        LOGGER.debug("Finding all {} entities sorted by {}", simpleTypeName, sort);
        List<T> found = repository.findAll(sort);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    public T findOne(ID id) {
        LOGGER.debug("Finding all {} entities with id {}", simpleTypeName, id);
        T found = repository.findOne(id);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    public void flush() {
        LOGGER.debug("Flushing all pending changes to the database");
        repository.flush();
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        LOGGER.info("Saving {} sequence into database", simpleTypeName);
        List<S> saved = repository.save(entities);
        LOGGER.debug("Successfully saved: {}", saved);

        return saved;
    }

    public <S extends T> S save(S entity) {
        System.out.println("Saving into database:" + entity);
        LOGGER.info("Saving {} into database", entity);
        S saved = repository.save(entity);
        LOGGER.debug("Successfully saved: {}", saved);

        return saved;
    }

    public T saveAndFlush(T entity) {
        LOGGER.info("Saving {} into database and flushing", entity);
        T saved = repository.saveAndFlush(entity);
        LOGGER.debug("Successfully saved: {}", saved);

        return saved;
    }
}