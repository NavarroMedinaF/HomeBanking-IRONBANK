package com.homebanking.homebanking.service;

import com.homebanking.homebanking.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransaction();
    Transaction getTransactionById(Long id);
    void saveTransation(Transaction transaction);
}
