package com.homebanking.homebanking.service.imprements;

import com.homebanking.homebanking.model.Card;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.model.ColorCard;
import com.homebanking.homebanking.model.TypeCard;
import com.homebanking.homebanking.repositories.CardRepository;
import com.homebanking.homebanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Card> getAllCard() {
        return cardRepository.findAll().stream().filter(Card::isCondition).collect(Collectors.toList());
    }

    @Override
    public void saveCard(Card card) {
    cardRepository.save(card);
    }

    @Override
    public Card findByColorTypeClientCardOwner(ColorCard color, TypeCard type, Client client) {
        return cardRepository.findByColorAndTypeAndClientCardOwner(color,type,client);
    }

    @Override
    public Set<Card> getAllCardClientAuth(Client client) {
        return client.getCards().stream().filter(card -> card.isCondition()==true).collect(Collectors.toSet());
    }

    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }
}
