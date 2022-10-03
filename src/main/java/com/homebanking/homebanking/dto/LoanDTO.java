package com.homebanking.homebanking.dto;


import com.homebanking.homebanking.model.ClientLoan;
import com.homebanking.homebanking.model.Loan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;

    private float percentage;
    private List<Integer> paymentLoan;


    /*CONTRUCTORES*/

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.paymentLoan = loan.getPaymentLoan();
        this.percentage=loan.getPercentage();

    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPaymentLoan() {
        return paymentLoan;
    }

    public float getPercentage() {
        return percentage;
    }
}
