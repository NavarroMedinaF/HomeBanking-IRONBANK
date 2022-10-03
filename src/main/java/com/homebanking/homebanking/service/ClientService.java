package com.homebanking.homebanking.service;

import com.homebanking.homebanking.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);

    Client findClietnByEmail(String email);

    void saveClient(Client client);
}
