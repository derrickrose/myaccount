package com.unosquare.exercise.myaccount.lib.web.controller;



import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import com.unosquare.exercise.myaccount.lib.service.UnosquareService;

import java.util.List;

/**
 * Superclass for each REST controller.
 * @author frils
 *
 * @param <T>
 */
public abstract class UnosquareController<T extends UnosquareEntity> {

    private UnosquareService<T> service;

    protected UnosquareController(UnosquareService<T> service) {
        this.service = service;
    }

    public List<T> findAll() {
        return service.findAll();
    }

    public T findById(Long id) {
        return service.findById(id);
    }

    public T save(T instance) {
        return service.save(instance);
    }

    public T update(T instance, Long id) {
        instance.setId(id);
        return service.update(instance);
    }

    public T merge(T instance, Long id) {
        instance.setId(id);
        return service.update(instance);
    }

    public void delete(Long id) {
        service.delete(id);
    }

}