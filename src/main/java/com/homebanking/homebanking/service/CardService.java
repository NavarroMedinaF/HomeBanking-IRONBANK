package com.homebanking.homebanking.service;

import com.homebanking.homebanking.model.*;

import java.util.List;
import java.util.Set;

public interface CardService {
    List<Card> getAllCard();
    void saveCard(Card card);
    Card findByColorTypeClientCardOwner(ColorCard color, TypeCard type, Client client);

    Set<Card> getAllCardClientAuth(Client client);
    Card findByNumber(String number);
}
