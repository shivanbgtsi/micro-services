package com.bank.loans.controller;

import com.bank.loans.entity.Customers;
import com.bank.loans.entity.Loans;
import com.bank.loans.repository.LoanRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @PostMapping
    public ResponseEntity<Void> addLoan(@RequestBody Loans loans) {
        Loans save = loanRepository.save(loans);
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("Why here");
        httpHeaders.add("loanNumber", save.getLoanNumber().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/details")
    public List<Loans> getLoan(@RequestBody Customers customers) {
        List<Loans> byCustomerId = loanRepository.findByCustomerId(customers.getCustomerId());

        System.out.println(byCustomerId);
        return byCustomerId;
    }
}
