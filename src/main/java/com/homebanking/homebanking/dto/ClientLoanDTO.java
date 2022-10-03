package com.homebanking.homebanking.dto;
import com.homebanking.homebanking.model.ClientLoan;


public class ClientLoanDTO {

    private long id;
    private long idLoan;
    private String name;
    private double amount;
    private int payments;

/*Contructor*/

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.idLoan = clientLoan.getLoanClientOwner().getId();
        this.name= clientLoan.getLoanClientOwner().getName();
        this.amount= clientLoan.getAmount();
        this.payments=clientLoan.getPayments();

    }

    /*Getter*/

    public long getId() {
        return id;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

 /*Setter*/
    public void setId(long id) {
     this.id = id;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}
