package com.homebanking.homebanking.service.imprements;

import com.homebanking.homebanking.model.ClientLoan;
import com.homebanking.homebanking.repositories.ClientLoanRepository;
import com.homebanking.homebanking.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImpl implements ClientLoanService {

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
