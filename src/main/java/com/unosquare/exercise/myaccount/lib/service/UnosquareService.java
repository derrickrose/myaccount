package com.unosquare.exercise.myaccount.lib.service;


import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import com.unosquare.exercise.myaccount.lib.facade.UnosquareFacade;

import java.util.List;

/**
 * @author frils
 *
 * @param <T>
 */
public abstract class UnosquareService<T extends UnosquareEntity> {

    private UnosquareFacade<T> facade;

    protected UnosquareService(UnosquareFacade<T> unosquareFacade) {
        this.facade = unosquareFacade;
    }

    public List<T> findAll() {
        return facade.findAll();
    }


    public T findById(Long id) {
        return facade.findById(id);
    }


    public T save(T instance) {
        return facade.save(instance);
    }


    public T update(T instance) {
        return facade.update(instance);
    }


    public void delete(Long id) {
        facade.delete(id);
    }
}

