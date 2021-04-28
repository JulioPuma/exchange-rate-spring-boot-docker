package com.angular.project.microservices.dao;

import com.angular.project.microservices.model.dto.CurrencyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyDto,Integer> {

    @Query("SELECT c FROM CurrencyDto c WHERE c.name = ?1 ")
    CurrencyDto findCurrencyDtoByName(String name);

}
