package com.homebanking.homebanking.model;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private boolean isActive;
    private AccountType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private Client clientOwner;

    @OneToMany(mappedBy="ownerAccount", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();


    /* Contructores */
    public Account() {
    }


    public Account(String number, LocalDateTime creationDate, double balance,boolean isActive,AccountType type, Client clientOwner) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.isActive = isActive;
        this.type = type;
        this.clientOwner = clientOwner;
    }

    /* Getters */

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
    public Client getClientOwner() {
        return clientOwner;
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }



    /* Setters */
    public void setNumber(String number) {
        this.number = number;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
    public void setClientOwner(Client clientOwner) {
        this.clientOwner = clientOwner;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
