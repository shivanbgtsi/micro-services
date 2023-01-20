package com.bank.loans.controller;

import com.bank.loans.entity.Loans;
import com.bank.loans.exceptions.LoanNumberNotFoundException;
import com.bank.loans.repository.LoanRepository;
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
        httpHeaders.add("customerId", save.getLoanNumber().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Loans getLoan(@PathVariable("id") Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> new LoanNumberNotFoundException("Loan number not found"));
    }
}
