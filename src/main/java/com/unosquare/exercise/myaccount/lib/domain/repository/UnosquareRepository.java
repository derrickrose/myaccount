package com.unosquare.exercise.myaccount.lib.domain.repository;

import com.unosquare.exercise.myaccount.lib.domain.entity.UnosquareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Super class for all repository.
 * @author frils
 *
 * @param <T>
 */
@NoRepositoryBean
public interface UnosquareRepository<T extends UnosquareEntity> extends JpaRepository<T, Long> {

}


