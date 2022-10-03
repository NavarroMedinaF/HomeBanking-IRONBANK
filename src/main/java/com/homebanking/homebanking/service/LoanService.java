package com.homebanking.homebanking.service;

import com.homebanking.homebanking.model.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> getAllLoans();
    Loan getLoanById(Long id);

    Loan saveLoan(Loan loan);

}
