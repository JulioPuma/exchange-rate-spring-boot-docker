package com.angular.project.microservices.services.exchangerate;

import com.angular.project.microservices.dao.CurrencyRepository;
import com.angular.project.microservices.dao.ExchangeRateRepository;
import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import com.angular.project.microservices.model.dto.CurrencyDto;
import com.angular.project.microservices.model.dto.ExchangeRateDto;
import com.angular.project.microservices.services.ExchangeRateInterface;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateInterface {

    private CurrencyRepository currencyRepository;
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public Single<ExchangeRateResponse> tipoCambio(ExchangeRate rate) {

        CurrencyDto currencyOrigen = currencyRepository.findCurrencyDtoByName(rate.getMonedaOrigen());
        CurrencyDto currencyDestino = currencyRepository.findCurrencyDtoByName(rate.getMonedaDestino());
        ExchangeRateDto exchangeRateDto = exchangeRateRepository.findByNames(currencyOrigen, currencyDestino);

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

        exchangeRateResponse.setMonedaOrigen(rate.getMonedaOrigen());
        exchangeRateResponse.setMonedaDestino(rate.getMonedaDestino());
        exchangeRateResponse.setTipoCambio(exchangeRateDto.getExchangeRate());
        exchangeRateResponse.setMonto(rate.getMonto());

        BigDecimal montoCalculado = rate.getMonto().multiply(new BigDecimal(exchangeRateDto.getExchangeRate()));
        exchangeRateResponse.setMontoTipoCambio(montoCalculado);

        return Single.just(exchangeRateResponse);
    }
}
