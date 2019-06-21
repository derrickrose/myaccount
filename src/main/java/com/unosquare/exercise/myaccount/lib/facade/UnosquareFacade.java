package com.unosquare.exercise.myaccount.lib.facade;


import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import com.unosquare.exercise.myaccount.lib.domain.repository.UnosquareRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author frils
 *
 * @param <T>
 */
@Transactional(readOnly = true)
public abstract class UnosquareFacade<T extends UnosquareEntity> {

    private UnosquareRepository<T> repository;

    protected UnosquareFacade(UnosquareRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        Optional<T> optional = repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Transactional(rollbackForClassName = {"Exception"})
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional(rollbackForClassName = {"Exception"})
    public T update(T entity) {
        return repository.save(entity);
    }

    @Transactional(rollbackForClassName = {"Exception"})
    public void delete(Long id) {
        repository.deleteById(id);
    }
}