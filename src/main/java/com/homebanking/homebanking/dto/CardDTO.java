package com.homebanking.homebanking.dto;

import com.homebanking.homebanking.model.Card;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.model.ColorCard;
import com.homebanking.homebanking.model.TypeCard;
import java.time.LocalDate;

public class CardDTO {

    /*PROPIEDADES*/
    private long id;
    private String cardholder;
    private TypeCard type;
    private ColorCard color;
    private String number;
    private int cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;

    private boolean condition;
    private Client clientCardOwner;

    /*CONTRUCTORES*/

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardholder = card.getCardholder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.condition=card.isCondition();

    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public TypeCard getType() {
        return type;
    }

    public ColorCard getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public Client getClientCardOwner() {
        return clientCardOwner;
    }

    public boolean isCondition() {
        return condition;
    }
}
