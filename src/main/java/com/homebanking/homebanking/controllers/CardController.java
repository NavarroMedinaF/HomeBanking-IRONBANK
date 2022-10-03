package com.homebanking.homebanking.controllers;


import com.homebanking.homebanking.dto.AccountDTO;
import com.homebanking.homebanking.dto.CardDTO;
import com.homebanking.homebanking.model.Card;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.model.ColorCard;
import com.homebanking.homebanking.model.TypeCard;
import com.homebanking.homebanking.service.CardService;
import com.homebanking.homebanking.service.ClientService;
import com.homebanking.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/card")
    public Set<CardDTO> getCardDTO(Authentication authentication){
        Client clientAunth=clientService.findClietnByEmail(authentication.getName());
        Set<Card> set= cardService.getAllCardClientAuth(clientAunth);
        return cardService.getAllCardClientAuth(clientAunth).stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    @PostMapping(path = "/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication,
                                             @RequestParam ColorCard color, @RequestParam TypeCard type) {

        if (color == null || type == null) {
            return new ResponseEntity<>("Faltan datos, reviselos por favor.", HttpStatus.BAD_REQUEST);
        }

        Client clientAuth = clientService.findClietnByEmail(authentication.getName());
        String numberCard = "";

        if (cardService.findByColorTypeClientCardOwner(color, type, clientAuth) == null || !cardService.findByColorTypeClientCardOwner(color, type, clientAuth).isCondition()) {
            boolean repeat = false;

            while (!repeat) {
                numberCard = CardUtils.getStringRandomNumber() + "-" + CardUtils.getStringRandomNumber() + "-" + CardUtils.getStringRandomNumber() + "-" + CardUtils.getStringRandomNumber();
                if (cardService.findByNumber(numberCard) == null) {
                    repeat = true;
                }
            }

            cardService.saveCard(new Card(clientAuth.getName() + " " + clientAuth.getLastName(), type, color, numberCard, CardUtils.getStringRandomcvv(), LocalDate.now().plusYears(5), LocalDate.now(), clientAuth, true));
            return new ResponseEntity<>("La tarjeta " + type + " " + color + " se ha creado exitosamente.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Usted ya posee la tarjeta " + type + " " + color + ".", HttpStatus.FORBIDDEN);
        }

    }

    @PatchMapping(path = "/clients/current/card")
    public ResponseEntity<Object> deleteCard(@RequestParam String number,
                                             Authentication authentication) {

        Client clientAuth = clientService.findClietnByEmail(authentication.getName());
        Card card = cardService.findByNumber(number);

        if (clientAuth == null) {
            return new ResponseEntity<>("Usted no tiene permiso para realizar esta accion", HttpStatus.FORBIDDEN);
        }

        if (card == null) {
            return new ResponseEntity<>("Numero de tarjeta incorrecto o inexistente.", HttpStatus.BAD_REQUEST);
        }

        card.setCondition(false);
        cardService.saveCard(card);

        return new ResponseEntity<>("La Tarjetar " + number + " a sido eliminada.",HttpStatus.ACCEPTED);
    }
}







