package com.homebanking.homebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@Entity
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
@GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name, lastName,email,password;

    @OneToMany(mappedBy="clientOwner", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="clientLoadOwner", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientLoan = new HashSet<>();

    @OneToMany(mappedBy="clientCardOwner", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    /* Contructores */
    public Client() {
    }



    public Client(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /* Getters */
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoan> getClientLoan() {
        return clientLoan;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public String getPassword() {
        return password;
    }

    /* Setters */

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setClientLoan(Set<ClientLoan> clientLoan) {
        this.clientLoan = clientLoan;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* Methods*/

    public void addAccount(Account account) {
        account.setClientOwner(this);
        accounts.add(account);
    }

    @JsonIgnore
    public List<Loan> getLoans(){
        return clientLoan.stream().map(ClientLoan::getLoanClientOwner).collect(toList());
    };
}
