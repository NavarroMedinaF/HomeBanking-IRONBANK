package com.homebanking.homebanking.dto;


import com.homebanking.homebanking.model.Client;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String name, lastName,email;
    private  Set<ClientLoanDTO> loans = new HashSet<>();
    private Set<AccountDTO> accounts = new HashSet<>();

    private Set<CardDTO> cards = new HashSet<>();

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.name = client.getName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts =client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

        this.loans = client.getClientLoan().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());

        this.cards =client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public ClientDTO() {
    }

 /*GETTERS*/

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

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }
}
