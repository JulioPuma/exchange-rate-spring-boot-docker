package com.angular.project.microservices.controllers;

import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import com.angular.project.microservices.model.api.ExchangeRateUpdate;
import com.angular.project.microservices.model.dto.ExchangeRateDto;
import com.angular.project.microservices.services.ExchangeRateInterface;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
@AllArgsConstructor
@Slf4j
public class ExchangeRateController {

  private ExchangeRateInterface exchangeRateInterface;

  @GetMapping("exchange-rate/{amount}/{from}/{to}")
  public Single<ExchangeRateResponse> tipoCambio(
          @PathVariable @Min(0) BigDecimal amount,
          @PathVariable @Length(min = 3, max = 3) String from,
          @PathVariable @Length(min = 3, max = 3) String to) {
    return exchangeRateInterface.tipoCambio(new ExchangeRate(amount,from,to));
  }

  @PutMapping("exchange-rate")
  public Completable updateTipoCambio(
          @RequestBody ExchangeRateUpdate exchangeRateUpdate
          ) {
    return exchangeRateInterface.updateTipoCambio(exchangeRateUpdate);
  }

  @GetMapping("exchange-rate/all")
  public Observable<ExchangeRateDto> listaTiposCambio(
  ) {
    return exchangeRateInterface.listaTiposCambio();
  }
}
