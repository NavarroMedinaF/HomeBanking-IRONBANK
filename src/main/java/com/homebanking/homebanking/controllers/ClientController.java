package com.homebanking.homebanking.controllers;

import com.homebanking.homebanking.dto.ClientDTO;
import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.AccountType;
import com.homebanking.homebanking.model.Client;
import com.homebanking.homebanking.service.AccountService;
import com.homebanking.homebanking.service.ClientService;
import com.homebanking.homebanking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/clients")
    public List<ClientDTO> getAllClientsDTO() {
        return clientService.getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientDTOById(@PathVariable Long id){
    return  new ClientDTO(clientService.getClientById(id));
    }

    @GetMapping("/clients/current")
    public ClientDTO getClientDTOAutentification(Authentication authentication){
        return new ClientDTO(clientService.findClietnByEmail(authentication.getName()));
    }


    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(
            @RequestParam String name, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {



        if (name.length() < 4 || lastName.length() < 4 ) {
            return new ResponseEntity<>("Alguno de los datos estan incompletos, por favor revisarlos", HttpStatus.FORBIDDEN);
        }
        if(!email.contains("@")){
            return new ResponseEntity<>("Ingrese un email valido por favor.", HttpStatus.FORBIDDEN);
        }
        if( password.length() < 6){
            return new ResponseEntity<>("El password debe tener mas de 6 caracteres", HttpStatus.FORBIDDEN);
        }
        if (clientService.findClietnByEmail(email) !=  null) {
            return new ResponseEntity<>("El correo electronico ya esta en uso", HttpStatus.FORBIDDEN);
        }
        if(email.contains("@admin")){
            return new ResponseEntity<>("No podes registrarte como administrador, por favor cambie el email",HttpStatus.FORBIDDEN);
        }

        String numberAccount="";
        boolean repeat = false;

        while(!repeat){
            numberAccount= AccountUtils.getStringRandomNumber();
            if (accountService.getAccountByNumber(numberAccount)==null){
                repeat=true;
            }
        }

        Client clientnew= new Client(name, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(clientnew);
        Account accountnew= new Account("VIN"+ numberAccount, LocalDateTime.now(), 0,true, AccountType.CURRENT,clientnew);
        accountService.saveAccount(accountnew);
        return new ResponseEntity<>(clientnew,HttpStatus.CREATED);

    }


}

