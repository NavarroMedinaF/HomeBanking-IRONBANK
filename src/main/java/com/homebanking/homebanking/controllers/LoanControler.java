package com.homebanking.homebanking.controllers;

import com.homebanking.homebanking.dto.LoanApplicationDTO;
import com.homebanking.homebanking.dto.LoanCreateDTO;
import com.homebanking.homebanking.dto.LoanDTO;
import com.homebanking.homebanking.model.*;
import com.homebanking.homebanking.repositories.*;
import com.homebanking.homebanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanControler {

    @Autowired
    private ClientLoanService clientLoanService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoanRepository loanRepository;


    @GetMapping(path = "/loans")
    public List<LoanDTO> getLoanDTO() {
        return loanService.getAllLoans().stream().map(LoanDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @PostMapping(path = "/loans")
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){

        Client clientAuth= clientService.findClietnByEmail(authentication.getName());
        Loan loanAuth= loanService.getLoanById(loanApplicationDTO.getId());
        Account accountAuth= accountService.getAccountByNumber(loanApplicationDTO.getAccountOwner());

        if (loanApplicationDTO.getAmount()<= 0 || loanApplicationDTO.getPayments()<=0 || loanApplicationDTO.getAccountOwner().isEmpty()){
            return new ResponseEntity<>("Tenes un campo vacio, por favor revise los datos ingresados", HttpStatus.FORBIDDEN);

        }

        if (loanAuth == null ){
            return new ResponseEntity<>("El prestamos que desea solicitar no esta entre nuestras ofertas",HttpStatus.FORBIDDEN);
        }

        if (loanAuth.getMaxAmount() < loanApplicationDTO.getAmount()){
            return new ResponseEntity<>("El monto solicitado exede la cantidad estipulada para el prestemo solicitado, por favor revise el monto q desea solicitar", HttpStatus.FORBIDDEN);
        }

        if (!loanAuth.getPaymentLoan().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("No estan disponible esa cantidad de cuotas para el prestamo que solicito, seleccione una correta por favor",HttpStatus.FORBIDDEN);
        }

        if (!clientAuth.getAccounts().contains(accountAuth)){
            return new ResponseEntity<>("No se ha podido auntenticar la cuenta de destino entre sus cuentas",HttpStatus.FORBIDDEN);
        }

        ClientLoan newLoan = new ClientLoan(loanApplicationDTO.getAmount()*1.2,loanApplicationDTO.getPayments(),clientAuth,loanAuth);
        Transaction transactionalloan = new Transaction(TransactionType.CREDITO,loanApplicationDTO.getAmount(),loanApplicationDTO.getAmount()+accountAuth.getBalance(),loanAuth.getName()+" "+ "loan approved", LocalDateTime.now(),true,accountAuth);
        accountAuth.setBalance(accountAuth.getBalance()+ loanApplicationDTO.getAmount());

        clientLoanService.saveClientLoan(newLoan);
        transactionService.saveTransation(transactionalloan);




        return new ResponseEntity<>("Su prestamo ha sido aprovado y entregado",HttpStatus.CREATED);
    }

    @PostMapping(path = "/admin/loans")
    public ResponseEntity<Object> createNewLoan(@RequestBody LoanCreateDTO loanCreateDTO,
                                                Authentication authentication){

        Client admin = clientService.findClietnByEmail(authentication.getName());
        if(loanCreateDTO.getName().isEmpty()||loanCreateDTO.getMaxAmount()<9999||loanCreateDTO.getPercentage()<1||loanCreateDTO.getPayments().size()<1){
            return new ResponseEntity<>("Faltan datos o son erroneos",HttpStatus.BAD_REQUEST);
        }
        if (admin==null){
            return new ResponseEntity<>("Cliente Admin no encontrado",HttpStatus.FORBIDDEN);
        }
        Loan newLoan = new Loan(loanCreateDTO.getName(),loanCreateDTO.getMaxAmount(),loanCreateDTO.getPercentage(),loanCreateDTO.getPayments());
        loanService.saveLoan(newLoan);
        return new ResponseEntity<>("El prestamo "+ loanCreateDTO.getName() + " ha sido creado.",HttpStatus.CREATED);

    }
}

