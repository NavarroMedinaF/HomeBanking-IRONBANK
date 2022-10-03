package com.homebanking.homebanking.controllers;

import com.homebanking.homebanking.dto.AccountDTO;
import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.AccountType;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.service.AccountService;
import com.homebanking.homebanking.service.ClientService;
import com.homebanking.homebanking.service.TransactionService;
import com.homebanking.homebanking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    public List<AccountDTO> getClientAccountsDTO(Authentication authentication) {
    Client clientAuth= clientService.findClietnByEmail(authentication.getName());

        return accountService.getAllAccounts().stream().filter(account -> account.getClientOwner()==clientAuth).map(AccountDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountDTOById(@PathVariable Long id, Authentication authentication){
        Client clientAuth = clientService.findClietnByEmail(authentication.getName());
        Account account = accountService.getAccountById(id);

        if(clientAuth.getAccounts().contains(account)){
            return new AccountDTO(accountService.getAccountById(id));
        }else{
            return null;
        }

    }

    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> createAccount(@RequestParam AccountType type, Authentication authentication){

        Client clientAuth = clientService.findClietnByEmail(authentication.getName());
        String numberAccount="";
        if (accountService.getAllAccounts().stream().filter(account -> account.getClientOwner() == clientAuth).count() < 3) {
            boolean repeat = false;

            while(!repeat){
                numberAccount= AccountUtils.getStringRandomNumber();
                if (accountService.getAccountByNumber(numberAccount)==null){
                    repeat=true;
                }
            }
            accountService.saveAccount(new Account("VIN-"+ numberAccount, LocalDateTime.now(), 0,true,type,clientAuth));
            return new ResponseEntity<>("La cuenta a sido creada",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Usted posee tres cuentas, no puede tener mas",HttpStatus.FORBIDDEN);
        }


   }
    @PatchMapping(path = "/clients/current/account")
    public ResponseEntity<Object> deleteAccount(@RequestParam Long id, Authentication authentication){
        Client clientAuth = clientService.findClietnByEmail(authentication.getName());
        Account accountAuth= accountService.getAccountById(id);

        if (clientAuth == null && accountAuth == null) {
            return new ResponseEntity<>("Usted no tiene permiso para realizar esta accion", HttpStatus.FORBIDDEN);
        }
        if(accountAuth.getBalance()>0){
        return new ResponseEntity<>("No se puede borrar una cuenta con saldo",HttpStatus.FORBIDDEN);

    }
        accountAuth.getTransactions().forEach(transaction -> transaction.setCondition(false));
        accountAuth.getTransactions().forEach(transaction -> transactionService.saveTransation(transaction));
        accountAuth.setActive(false);
        accountService.saveAccount(accountAuth);


        return new ResponseEntity<>("La cuenta a sido eliminada",HttpStatus.ACCEPTED);
    }
}


