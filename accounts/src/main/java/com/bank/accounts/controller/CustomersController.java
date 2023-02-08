package com.bank.accounts.controller;

import com.bank.accounts.entity.Customers;
import com.bank.accounts.exceptions.CustomerNotFoundException;
import com.bank.accounts.models.Cards;
import com.bank.accounts.models.CustomerDetails;
import com.bank.accounts.models.Loans;
import com.bank.accounts.repository.CustomersRepository;
import com.bank.accounts.service.client.CardsService;
import com.bank.accounts.service.client.LoanService;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;
    
    @Autowired
    private LoanService loanService;

    @Autowired
    private CardsService cardsService;

    @PostMapping
    public ResponseEntity<Void> addCustomers(@RequestBody Customers customers){
        Customers save = customersRepository.save(customers);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("customerId",save.getCustomerId().toString());
        return  new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
//    @CircuitBreaker(name = "customerCircuitBreaker",fallbackMethod = "proxyCustomerDetails")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "proxyCustomerDetails")
    public CustomerDetails getCustomer(@PathVariable("id") Integer id){
    	
        Customers customers = customersRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        List<Loans> loansDetails = loanService.getLoansDetails(customers);
        List<Cards> cardsDetails = cardsService.getCardsDetails(customers);
        CustomerDetails customerDetails = new CustomerDetails(customers, loansDetails,cardsDetails);
        return  customerDetails;
    }

    private CustomerDetails proxyCustomerDetails(Integer id, Throwable t){
        Customers customers = customersRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        List<Loans> loansDetails = loanService.getLoansDetails(customers);
        List<Cards> cardsDetails = cardsService.getCardsDetails(customers);
        CustomerDetails customerDetails = new CustomerDetails(customers, loansDetails,cardsDetails);
        return  customerDetails;
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
