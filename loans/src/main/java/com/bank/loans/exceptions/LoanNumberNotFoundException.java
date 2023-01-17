package com.bank.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNumberNotFoundException extends RuntimeException {
    public LoanNumberNotFoundException(String message) {
        super(message);
    }
}
