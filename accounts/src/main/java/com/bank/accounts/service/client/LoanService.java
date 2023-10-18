package com.bank.accounts.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.accounts.entity.Customers;
import com.bank.accounts.models.Loans;

@FeignClient("loans")
public interface LoanService {

	@RequestMapping(method = RequestMethod.POST, value = "/api/details")
	List<Loans> getLoansDetails(@RequestBody Customers customers);

}
