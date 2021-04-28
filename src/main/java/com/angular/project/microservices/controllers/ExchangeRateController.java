package com.angular.project.microservices.controllers;

import com.angular.project.microservices.dao.CurrencyRepository;
import com.angular.project.microservices.dao.ExchangeRateRepository;
import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import com.angular.project.microservices.model.dto.CurrencyDto;
import com.angular.project.microservices.services.ExchangeRateInterface;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
@AllArgsConstructor
@Slf4j
public class ExchangeRateController {

  private ExchangeRateInterface exchangeRateInterface;

  @PostMapping("exchange-rate")
  public Single<ExchangeRateResponse> tipoCambio(@RequestBody(required = false) ExchangeRate exchangeRate) {
    return exchangeRateInterface.tipoCambio(exchangeRate);
  }
}
