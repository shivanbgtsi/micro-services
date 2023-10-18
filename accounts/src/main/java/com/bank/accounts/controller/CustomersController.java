package com.bank.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accounts.entity.Customers;
import com.bank.accounts.exceptions.CustomerNotFoundException;
import com.bank.accounts.models.Cards;
import com.bank.accounts.models.CustomerDetails;
import com.bank.accounts.models.Loans;
import com.bank.accounts.repository.CustomersRepository;
import com.bank.accounts.service.client.CardsService;
import com.bank.accounts.service.client.LoanService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api")
public class CustomersController {

	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private LoanService loanService;

	@Autowired
	private CardsService cardsService;

	private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);

	@PostMapping
	public ResponseEntity<Void> addCustomers(@RequestBody Customers customers) {
		Customers save = customersRepository.save(customers);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("customerId", save.getCustomerId().toString());
		logger.info("Adding customer " + save.getCustomerId());
		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
//    @CircuitBreaker(name = "customerCircuitBreaker",fallbackMethod = "proxyCustomerDetails")
	@Retry(name = "retryForCustomerDetails", fallbackMethod = "proxyCustomerDetails")
	public CustomerDetails getCustomer(@PathVariable("id") Integer id) {

		Customers customers = customersRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		List<Loans> loansDetails = loanService.getLoansDetails(customers);
		List<Cards> cardsDetails = cardsService.getCardsDetails(customers);
		logger.info("Fetching customers details " + id);
		CustomerDetails customerDetails = new CustomerDetails(customers, loansDetails, cardsDetails);
		return customerDetails;
	}

	private CustomerDetails proxyCustomerDetails(Integer id, Throwable t) {
		Customers customers = customersRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		List<Loans> loansDetails = loanService.getLoansDetails(customers);
		List<Cards> cardsDetails = cardsService.getCardsDetails(customers);
		CustomerDetails customerDetails = new CustomerDetails(customers, loansDetails, cardsDetails);
		return customerDetails;
	}

	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, Welcome to microservice";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to microservice";
	}

}
