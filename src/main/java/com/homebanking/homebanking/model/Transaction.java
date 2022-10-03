package com.homebanking.homebanking.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
/*PROPIEDADES Y METODOS*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private TransactionType type;
    private double amount,currentAmount;
    private String description;
    private LocalDateTime date;

    private boolean condition;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ownerAccount_id")
    private Account ownerAccount;


    /*Contructores*/

    public Transaction() {
    }
    public Transaction(TransactionType type, double amount, double currentAmount, String description, LocalDateTime date,boolean condition, Account ownerAccount) {
        this.type = type;
        this.amount = amount;
        this.currentAmount = currentAmount;
        this.description = description;
        this.date = date;
        this.condition=condition;
        this.ownerAccount = ownerAccount;
    }

    /*Getters*/

    public long getId() {
        return id;
    }
    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public double getCurrentAmount(){return currentAmount;}
    public String getDescription() {
        return description;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public Account getOwnerAccount() {
        return ownerAccount;
    }

    public boolean isCondition() {
        return condition;
    }
    /*Setters*/



    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setOwnerAccount(Account ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}
