package com.homebanking.homebanking.controllers;

import com.homebanking.homebanking.dto.TransactionDTO;
import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.model.Transaction;
import com.homebanking.homebanking.model.TransactionType;
import com.homebanking.homebanking.service.AccountService;
import com.homebanking.homebanking.service.ClientService;
import com.homebanking.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;


    @GetMapping("/transactions")
    public List<TransactionDTO> getAccountsTransactionDTO(){
        return transactionService.getAllTransaction().stream().map(TransactionDTO::new).collect(toList());
    }

    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionDTOById(@PathVariable Long id){
        return new TransactionDTO(transactionService.getTransactionById(id));
    }

    @Transactional
    @PostMapping(path = "/clients/current/transactions")
    public ResponseEntity<Object> transaction(
            Authentication authentication,
            @RequestParam String accountOrigin,
            @RequestParam String accountDestiny,
            @RequestParam String description,
            @RequestParam double amount){

        Client clientAuthOrigin= clientService.findClietnByEmail(authentication.getName());
        Account accountTranferOring=accountService.getAccountByNumber(accountOrigin);
        Account accountTranferDestiny=accountService.getAccountByNumber(accountDestiny);
        //amount==0

        if( Double.toString(amount).isEmpty()  || accountOrigin.isEmpty() || accountDestiny.isEmpty() || description.isEmpty()){
            return new ResponseEntity<>("Faltan datos o son erroneos",HttpStatus.FORBIDDEN);
        }
        if(accountTranferOring==null){
            return new ResponseEntity<>("La cuenta de origen no existe",HttpStatus.FORBIDDEN);
        }
        if(accountTranferDestiny==null){
            return new ResponseEntity<>("La cuenta de destino no existe",HttpStatus.FORBIDDEN);
        }
        if (accountOrigin.equalsIgnoreCase(accountDestiny)){
            return new ResponseEntity<>("Las cuentas son iguales, elija otra para el destino de la transaccion",HttpStatus.FORBIDDEN);
        }
        if(clientAuthOrigin.getAccounts().size()==0){
            return new ResponseEntity<>("La cuenta de origen no existe entre las que usted posee",HttpStatus.FORBIDDEN);
        }

        if(accountTranferOring.getBalance()<amount){
            return new ResponseEntity<>("La cuenta de origen no posee suficientes fondos para realizar esta operacion.",HttpStatus.FORBIDDEN);
        }

        Transaction transactionDebit = new Transaction(TransactionType.DEBITO,-amount,accountTranferOring.getBalance()-amount,description + " " + accountTranferOring.getNumber(), LocalDateTime.now(),true,accountTranferOring);
        Transaction transactionCredit = new Transaction(TransactionType.CREDITO,amount,accountTranferDestiny.getBalance()+amount ,description + " " + accountTranferDestiny.getNumber(), LocalDateTime.now(),true,accountTranferDestiny);
        transactionService.saveTransation(transactionDebit);
        transactionService.saveTransation(transactionCredit);
        accountTranferOring.setBalance(accountTranferOring.getBalance()-amount);
        accountTranferDestiny.setBalance(accountTranferDestiny.getBalance()+amount);
        accountService.saveAccount(accountTranferOring);
        accountService.saveAccount(accountTranferDestiny);
        return new ResponseEntity<>("La transaccion se ha realizado con exito.", HttpStatus.CREATED);
    }

}
