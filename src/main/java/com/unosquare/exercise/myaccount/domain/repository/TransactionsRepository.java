package com.unosquare.exercise.myaccount.domain.repository;

import com.unosquare.exercise.myaccount.domain.entity.Transaction;
import com.unosquare.exercise.myaccount.lib.domain.repository.UnosquareRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frils
 *
 */
@Repository
public interface TransactionsRepository extends UnosquareRepository<Transaction> {

    @Query(value = "SELECT * FROM transaction t JOIN account a ON a.id = t.account_id where a.id = ? ORDER BY t.date DESC LIMIT 5", nativeQuery = true)
    List<Transaction> getLatest5TransactionsByAccountId(@Param("account_id") Long account_id);

    @Query(value = "SELECT * FROM transaction t JOIN account a ON a.id = t.account_id where a.id = ? AND t.currency = ? ", nativeQuery = true)
    List<Transaction> getTransactionsByAccountIdAndCurrency(@Param("account_id") Long account_id, @Param("currency") String currency);

    @Query(value = "SELECT * FROM transaction t JOIN account a ON a.id = t.account_id where a.id = ? ", nativeQuery = true)
    List<Transaction> getTransactionsByAccountId(@Param("account_id") Long account_id);


}
