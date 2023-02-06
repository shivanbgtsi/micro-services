package com.bank.accounts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customers {

    @Column(name = "customerid")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name ="lastname")
    private String lastName;

    @Column(name ="email")
    private String email;

    @Column(name ="phonenumber")
    private String phoneNumber;

    @Column(name = "createdate")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate createDate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Accounts> accounts;

}
