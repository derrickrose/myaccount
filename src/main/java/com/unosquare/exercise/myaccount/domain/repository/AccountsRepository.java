package com.unosquare.exercise.myaccount.domain.repository;

import com.unosquare.exercise.myaccount.domain.entity.Account;
import com.unosquare.exercise.myaccount.lib.domain.repository.UnosquareRepository;
import org.springframework.stereotype.Repository;

/**
 * Used for the interaction with data base.
 * @author frils
 *
 */
@Repository
public interface AccountsRepository extends UnosquareRepository<Account> {

}
