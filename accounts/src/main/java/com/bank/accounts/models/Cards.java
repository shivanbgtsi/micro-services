package com.bank.accounts.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cards {

    private Integer cardNumber;

    private Integer customerId;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;

    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate createDate;

}
