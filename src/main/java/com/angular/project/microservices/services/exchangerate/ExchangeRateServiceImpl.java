package com.angular.project.microservices.services.exchangerate;

import com.angular.project.microservices.dao.CurrencyRepository;
import com.angular.project.microservices.dao.ExchangeRateRepository;
import com.angular.project.microservices.model.api.ExchangeRate;
import com.angular.project.microservices.model.api.ExchangeRateNew;
import com.angular.project.microservices.model.api.ExchangeRateResponse;
import com.angular.project.microservices.model.api.ExchangeRateUpdate;
import com.angular.project.microservices.model.dto.CurrencyDto;
import com.angular.project.microservices.model.dto.ExchangeRateDto;
import com.angular.project.microservices.services.ExchangeRateInterface;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;

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
        exchangeRateResponse.setTipoCambio(exchangeRateDto.getExchangeRate().setScale(3, RoundingMode.HALF_UP));
        exchangeRateResponse.setMonto(rate.getMonto());

        BigDecimal montoCalculado = rate.getMonto()
                .multiply(exchangeRateDto.getExchangeRate())
                .setScale(3, RoundingMode.HALF_UP);
        exchangeRateResponse.setMontoTipoCambio(montoCalculado);

        return Single.just(exchangeRateResponse);
    }

    @Override
    public Completable updateTipoCambio(ExchangeRateUpdate rate) {
        CurrencyDto currencyOrigen = currencyRepository.findCurrencyDtoByName(rate.getMonedaOrigen());
        CurrencyDto currencyDestino = currencyRepository.findCurrencyDtoByName(rate.getMonedaDestino());
        ExchangeRateDto exchangeRateDto = exchangeRateRepository.findByNames(currencyOrigen, currencyDestino);
        exchangeRateDto.setExchangeRate(rate.getRate());
        exchangeRateRepository.save(exchangeRateDto);
        return Completable.complete();
    }

    @Override
    public Observable<ExchangeRateDto> listaTiposCambio() {
        return Observable.fromIterable(exchangeRateRepository.findAll());
    }

    @Override
    public Completable nuevoTipoCambio(ExchangeRateUpdate exchangeRateNew) {


        CurrencyDto currencyDtoauxOrigen = currencyRepository.findCurrencyDtoByName(exchangeRateNew.getMonedaOrigen());

                if(currencyDtoauxOrigen == null){
                    currencyDtoauxOrigen = new CurrencyDto();
                    currencyDtoauxOrigen.setName(exchangeRateNew.getMonedaOrigen());
                    currencyDtoauxOrigen = currencyRepository.save(currencyDtoauxOrigen);
                }

        CurrencyDto currencyDtoauxDestino = currencyRepository.findCurrencyDtoByName(exchangeRateNew.getMonedaDestino());

                if(currencyDtoauxDestino == null){
                    currencyDtoauxDestino = new CurrencyDto();
                    currencyDtoauxDestino.setName(exchangeRateNew.getMonedaDestino());
                    currencyDtoauxDestino = currencyRepository.save(currencyDtoauxDestino);
                }





        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setExchangeRate(exchangeRateNew.getRate());
        exchangeRateDto.setCurrencyBase(currencyDtoauxOrigen);
        exchangeRateDto.setCurrencyDestiny(currencyDtoauxDestino);
        //exchangeRateDto.setDate(new Date().toLocalDate());


        exchangeRateRepository.save(exchangeRateDto);
        return Completable.complete();
    }
}
