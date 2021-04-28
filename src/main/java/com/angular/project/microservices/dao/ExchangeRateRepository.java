package com.angular.project.microservices.dao;
import com.angular.project.microservices.model.dto.CurrencyDto;
import com.angular.project.microservices.model.dto.ExchangeRateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateDto,Integer> {

    @Query("SELECT e FROM ExchangeRateDto e WHERE e.currencyBase = ?1 and e.currencyDestiny = ?2")
    ExchangeRateDto findByNames(CurrencyDto currencyOrigen, CurrencyDto currencyDestino);



}
