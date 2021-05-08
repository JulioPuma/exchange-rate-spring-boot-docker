package com.angular.project.microservices.services;

import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateNew;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import com.angular.project.microservices.model.api.ExchangeRateUpdate;
import com.angular.project.microservices.model.dto.ExchangeRateDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ExchangeRateInterface {

    Single<ExchangeRateResponse> tipoCambio(ExchangeRate rate);

    Completable updateTipoCambio(ExchangeRateUpdate rate);

    Observable<ExchangeRateDto> listaTiposCambio();

    Completable nuevoTipoCambio(ExchangeRateUpdate exchangeRateNew);
}
