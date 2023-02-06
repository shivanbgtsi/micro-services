package com.bank.loans.repository;

import com.bank.loans.entity.Loans;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loans,Integer> {
	
	List<Loans> findByCustomerId(Integer customerId);
	
}
