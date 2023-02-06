package com.bank.accounts.service.client;

import com.bank.accounts.entity.Customers;
import com.bank.accounts.models.Cards;
import com.bank.accounts.models.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsService {

    @RequestMapping(method = RequestMethod.POST, value = "bank/cards/details")
    List<Cards> getCardsDetails(@RequestBody Customers customers);
}
