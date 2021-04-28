package com.angular.project.microservices.services;

import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import io.reactivex.Single;

public interface ExchangeRateInterface {

    Single<ExchangeRateResponse> tipoCambio(ExchangeRate rate);
}
