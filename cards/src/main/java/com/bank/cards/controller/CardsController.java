package com.bank.cards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cards.entity.Cards;
import com.bank.cards.models.Customers;
import com.bank.cards.repository.CardRepository;

@RestController
@RequestMapping("/api")
public class CardsController {

	private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

	@Autowired
	private CardRepository loanRepository;

	@PostMapping
	public ResponseEntity<Void> addCards(@RequestBody Cards cards) {
		Cards save = loanRepository.save(cards);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("cardNumber", save.getCardNumber().toString());
		logger.info("Provinding cards " + cards.getCustomerId());
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@PostMapping("/details")
	public List<Cards> getCards(@RequestBody Customers customers) {
		logger.info("Fetching cards details of " + customers.getCustomerId());
		return loanRepository.findByCustomerId(customers.getCustomerId());
	}
}
