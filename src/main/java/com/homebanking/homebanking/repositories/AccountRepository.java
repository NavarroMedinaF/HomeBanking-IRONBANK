package com.homebanking.homebanking.repositories;

import com.homebanking.homebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

   Account findByNumber(String number);
}
