package com.bank.accounts.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts {

    @Column(name = "accountnumber")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;

    @Column(name = "branchaddress")
    private String branchAddress;

    @Column(name ="accounttype")
    private String accountType;

    @Column(name = "createdate")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "custumerid")
    private Customers customer;

}
