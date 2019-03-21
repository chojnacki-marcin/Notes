package com.Notes.service;

import com.Notes.dto.AccountDto;
import com.Notes.entity.Account;

import java.util.Optional;

public interface AccountService {
    boolean createAccount(AccountDto accountDto);
    Optional<Account> findAccountByEmail(String email);
}
