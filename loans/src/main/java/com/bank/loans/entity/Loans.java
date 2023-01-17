package com.bank.loans.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loans {

    @Column(name = "loannumber")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer loanNumber;

    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "loantype")
    private String loanType;

    @Column(name = "totalloan")
    private Integer totalLoan;

    @Column(name = "amountpaid")
    private Integer amountPaid;

    @Column(name = "outstandingamount")
    private Integer outstandingAmount;

    @Column(name = "startdt")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate startDate;

    @Column(name = "enddt")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate endDate;

    @Column(name = "createdate")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate createDate;

}