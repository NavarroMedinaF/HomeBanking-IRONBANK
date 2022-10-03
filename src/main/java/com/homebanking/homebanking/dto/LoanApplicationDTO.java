package com.homebanking.homebanking.dto;

import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.Loan;

import java.util.List;

public class LoanApplicationDTO {

    private long id;
    private double amount;
    private int payments;
    private String accountOwner;

    public LoanApplicationDTO() {
    }


    /*GETTERS*/

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getAccountOwner() {
        return accountOwner;
    }


}
