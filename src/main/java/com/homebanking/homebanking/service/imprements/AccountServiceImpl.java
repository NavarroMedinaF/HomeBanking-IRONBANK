package com.homebanking.homebanking.service.imprements;

import com.homebanking.homebanking.model.Account;
import com.homebanking.homebanking.repositories.AccountRepository;
import com.homebanking.homebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll().stream().filter(Account::isActive).collect(Collectors.toList());
    }

    @Override
    public Account getAccountById(Long id) {
            if(accountRepository.findById(id).get().isActive()){
                return accountRepository.findById(id).get();
            }else{
                return null;
            }

    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }


}
