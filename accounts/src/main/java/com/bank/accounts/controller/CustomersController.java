package com.bank.accounts.controller;

import com.bank.accounts.entity.Customers;
import com.bank.accounts.exceptions.CustomerNotFoundException;
import com.bank.accounts.repository.CustomersRepository;
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

    @PostMapping
    public ResponseEntity<Void> addCustomers(@RequestBody Customers customers){
        Customers save = customersRepository.save(customers);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("customerId",save.getCustumerId().toString());
        return  new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Customers getCustomer(@PathVariable("id") Integer id){
        return customersRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}
