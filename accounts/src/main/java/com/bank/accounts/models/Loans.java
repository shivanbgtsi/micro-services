package com.bank.accounts.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Loans {

	private Integer loanNumber;

    private Integer customerId;

    private String loanType;
 
    private Integer totalLoan;
 
    private Integer amountPaid;

    private Integer outstandingAmount;
 
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate startDate;
 
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate endDate;

    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate createDate;
}
