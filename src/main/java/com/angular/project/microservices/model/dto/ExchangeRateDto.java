package com.angular.project.microservices.model.dto;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "exchange_rate_t002")
@Getter
@ToString
public class ExchangeRateDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exchange_rate")
    private Float exchangeRate;

    @Column(name = "date")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_base")
    private CurrencyDto currencyBase;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_destiny")
    private CurrencyDto currencyDestiny;
}
