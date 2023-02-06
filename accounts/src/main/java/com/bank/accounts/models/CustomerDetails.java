package com.bank.accounts.models;

import java.util.List;

import com.bank.accounts.entity.Customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails {
	
	private Customers customers;
	
	private List<Loans> loansDetails;

	private List<Cards> cardsDetails;

}
