package com.homebanking.homebanking.service;

import com.homebanking.homebanking.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    void saveAccount(Account account);

     Account getAccountByNumber(String number);

}
