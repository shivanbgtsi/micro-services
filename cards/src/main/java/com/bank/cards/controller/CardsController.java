package com.bank.cards.controller;

import com.bank.cards.entity.Cards;
import com.bank.cards.exceptions.CardNotFoundException;
import com.bank.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Cards getCard(@PathVariable("id") Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card Number not found"));
    }
}
