package com.homebanking.homebanking.repositories;

import com.homebanking.homebanking.model.Card;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.model.ColorCard;
import com.homebanking.homebanking.model.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByColorAndTypeAndClientCardOwner(ColorCard color, TypeCard type, Client client);
    Card findByNumber(String number);
}
