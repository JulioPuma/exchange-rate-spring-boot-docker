package com.angular.project.microservices.model.api;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRateUpdate {


    @NotNull
    @Length(min = 3, max = 3)
    private String monedaOrigen;
    @NotNull
    @Length(min = 3, max = 3)
    private String monedaDestino;
    @NotNull
    @Min(0)
    private BigDecimal rate;
}
