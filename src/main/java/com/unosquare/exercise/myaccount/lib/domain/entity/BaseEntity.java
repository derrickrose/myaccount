package com.unosquare.exercise.myaccount.lib.domain.entity;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Used to force having both of the methods inside all entity.
 * @author frils
 *
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public interface BaseEntity<T extends Serializable> extends Serializable {

    T getId();

    void setId(T id);

}


