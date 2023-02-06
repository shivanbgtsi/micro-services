package com.bank.cards.controller;

import com.bank.cards.entity.Cards;
import com.bank.cards.models.Customers;
import com.bank.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardRepository loanRepository;

    @PostMapping
    public ResponseEntity<Void> addCards(@RequestBody Cards cards) {
        Cards save = loanRepository.save(cards);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("cardNumber", save.getCardNumber().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/details")
    public List<Cards> getCards(@RequestBody Customers customers) {
        return loanRepository.findByCustomerId(customers.getCustomerId());
    }
}
