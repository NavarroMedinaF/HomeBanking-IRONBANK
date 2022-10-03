package com.homebanking.homebanking.dto;

import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.AccountType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class    AccountDTO {
/*PROPIEDADES Y METODOS*/
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private boolean isActive;

    private AccountType type;
    private Set<TransactionDTO> transactions = new HashSet<>();

    /*CONTRUCTORES*/
    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.isActive= account.isActive();
        this.type=account.getType();
        this.transactions =account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
    }

    /*GETTERS*/
    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public AccountType getType() {
        return type;
    }

    public Set<TransactionDTO> getTransaction() {
        return transactions;
    }


}
