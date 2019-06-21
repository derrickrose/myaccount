package com.unosquare.exercise.myaccount.facade;

import com.unosquare.exercise.myaccount.domain.entity.Balance;
import com.unosquare.exercise.myaccount.domain.repository.BalancesRepository;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.exception.EntityNotFoundException;
import com.unosquare.exercise.myaccount.lib.facade.UnosquareFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author frils
 *
 */
@Component
public class BalancesFacade extends UnosquareFacade<Balance> {

    @Autowired
    public BalancesFacade(BalancesRepository repository) {
        super(repository);
    }

    public Balance getBalance(Long id) {
        Balance balance = this.findById(id);
        if (balance == null) throw new EntityNotFoundException("Balance not found with id " + id + ".");
        return balance;
    }

    @Transactional
    public Balance performDeposit(Long id, Float amount) {
        Balance balance = getBalance(id);
        balance.setAmount(balance.getAmount().floatValue() + amount.floatValue());
        return updateBalance(balance);
    }

    @Transactional
    public Balance performDeposit(Balance balance, Float amount) {
        balance.setAmount(balance.getAmount().floatValue() + amount.floatValue());
        return updateBalance(balance);
    }

    @Transactional
    public Balance performWithDraw(Long id, Float amount) {
        Balance balance = getBalance(id);
        balance.setAmount(balance.getAmount().floatValue() - amount.floatValue());
        return updateBalance(balance);
    }

    @Transactional
    public Balance performWithDraw(Balance balance, Float amount) {
        balance.setAmount(balance.getAmount().floatValue() - amount.floatValue());
        return updateBalance(balance);
    }

    private Balance updateBalance(Balance balance) {
        balance = this.update(balance);
        if (balance == null)
            throw new InternalException("Encountered an unexpected error.");
        return balance;
    }


}
