package com.bank.cards.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cards {

    @Column(name = "cardnumber")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cardNumber;

    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "cardtype")
    private String cardType;

    @Column(name = "totallimit")
    private int totalLimit;

    @Column(name = "amountused")
    private int amountUsed;

    @Column(name = "availableamount")
    private int availableAmount;

    @Column(name = "createdate")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate createDate;

}
