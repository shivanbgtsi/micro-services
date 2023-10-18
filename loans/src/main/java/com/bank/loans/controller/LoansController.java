package com.bank.loans.controller;

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

import com.bank.loans.entity.Customers;
import com.bank.loans.entity.Loans;
import com.bank.loans.repository.LoanRepository;

@RestController
@RequestMapping("/api")
public class LoansController {

	@Autowired
	private LoanRepository loanRepository;

	private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

	@PostMapping
	public ResponseEntity<Void> addLoan(@RequestBody Loans loans) {
		Loans save = loanRepository.save(loans);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("loanNumber", save.getLoanNumber().toString());
		logger.info("Providing load to " + loans.getCustomerId());
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@PostMapping("/details")
	public List<Loans> getLoan(@RequestBody Customers customers) {
		List<Loans> byCustomerId = loanRepository.findByCustomerId(customers.getCustomerId());
		logger.info("Fetching loan details " + customers.getCustomerId());

		return byCustomerId;
	}
}
