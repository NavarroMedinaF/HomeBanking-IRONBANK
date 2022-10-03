package com.homebanking.homebanking.service.imprements;

import com.homebanking.homebanking.model.Loan;
import com.homebanking.homebanking.repositories.LoanRepository;
import com.homebanking.homebanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }


}
