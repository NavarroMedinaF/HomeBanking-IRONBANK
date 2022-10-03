package com.homebanking.homebanking.service.imprements;

import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.model.Transaction;
import com.homebanking.homebanking.repositories.TransactionRepository;
import com.homebanking.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll().stream().filter(Transaction::isCondition).collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionById(Long id) {
        if(transactionRepository.findById(id).get().isCondition()){
            return transactionRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveTransation(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
