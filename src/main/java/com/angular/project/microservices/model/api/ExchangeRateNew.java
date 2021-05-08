package com.angular.project.microservices.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRateNew {

    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
