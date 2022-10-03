package com.homebanking.homebanking.dto;

import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.Transaction;
import com.homebanking.homebanking.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    /*PROPIEDADES Y METODOS*/
    private long id;
    private TransactionType type;
    private double amount,currentAmount;
    private String description;
    private LocalDateTime date;



    /*CONTRUCTORES*/

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.currentAmount=transaction.getCurrentAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();

    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getCurrentAmount(){
        return currentAmount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }



}
