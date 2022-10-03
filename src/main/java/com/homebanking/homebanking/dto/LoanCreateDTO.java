package com.homebanking.homebanking.dto;

import java.util.List;

public class LoanCreateDTO {
    private long id;

    private String name;
    private double maxAmount;

    private float percentage;
    private List<Integer> payments;

    public LoanCreateDTO() {
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

    public float getPercentage() {
        return percentage;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
