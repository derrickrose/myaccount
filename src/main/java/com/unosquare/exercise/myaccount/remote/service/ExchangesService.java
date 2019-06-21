package com.unosquare.exercise.myaccount.remote.service;

import com.unosquare.exercise.myaccount.exception.CustomException;
import com.unosquare.exercise.myaccount.exception.InternalException;
import com.unosquare.exercise.myaccount.exception.UnexpectedException;
import com.unosquare.exercise.myaccount.remote.model.RemoteConverterModel;
import com.unosquare.exercise.myaccount.util.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author frils
 *
 */
@Log4j
@Service
public class ExchangesService {

    @Value("${currency.converter.url}")
    private String currenyConverterBaseUrl;

    @Autowired
    RestTemplate restTemplate;

    public Float getExchangeRate(String transactionCurrency) {

        final String uri = currenyConverterBaseUrl + Constants.DEFAULT_CURRENCY;
        RemoteConverterModel remoteConverterModel;
        try {
            remoteConverterModel = restTemplate.getForObject(uri, RemoteConverterModel.class);
        } catch (Error e) {
            log.error(e);
            throw new InternalException("UnosquareError on attempt to list daily rates.");
        }

        try {
            validateRemoteConverterModel(remoteConverterModel);
        } catch (CustomException e) {
            log.error(e);
            throw new InternalException(e.getMessage());
        }

        try {
            validateTransactionCurrencyWithAcceptedCurrency(transactionCurrency, remoteConverterModel);
        } catch (CustomException e) {
            log.error(e);
            throw new UnexpectedException(e.getMessage());
        }

        return remoteConverterModel.getRates().get(transactionCurrency.toUpperCase());
    }

    private static final void validateRemoteConverterModel(RemoteConverterModel remoteConverterModel) throws CustomException {
        if (remoteConverterModel == null || remoteConverterModel.getRates() == null || remoteConverterModel.getRates().isEmpty())
            throw new CustomException("Invalid Remote Converter Response Object.");
    }

    private static final void validateTransactionCurrencyWithAcceptedCurrency(String transactionCurrency, RemoteConverterModel remoteConverterModel) throws CustomException {
        if (!remoteConverterModel.getRates().containsKey(transactionCurrency.toUpperCase()))
            throw new CustomException("Invalid Currency " + transactionCurrency + ".");
    }

}
