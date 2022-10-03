package com.homebanking.homebanking.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity
public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long Id;
    private double Amount;
    private int payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clientLoadOwner_id")
    private Client clientLoadOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loanOwner_id")
    private Loan loanClientOwner;

    public ClientLoan() {
    }

    public ClientLoan(double amount, int payments, Client clientLoadOwner, Loan loanClientOwner) {
        Amount = amount;
        this.payments = payments;
        this.clientLoadOwner = clientLoadOwner;
        this.loanClientOwner = loanClientOwner;
    }

    public long getId() {
        return Id;
    }

    public double getAmount() {
        return Amount;
    }

    public int getPayments() {
        return payments;
    }

    public Client getClientLoadOwner() {
        return clientLoadOwner;
    }

    public Loan getLoanClientOwner() {
        return loanClientOwner;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public void setClientLoadOwner(Client clientLoadOwner) {
        this.clientLoadOwner = clientLoadOwner;
    }

    public void setLoanClientOwner(Loan loanClientOwner) {
        this.loanClientOwner = loanClientOwner;
    }

    /*Metodos*/



}
