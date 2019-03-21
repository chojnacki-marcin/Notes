package com.Notes.service;


import com.Notes.dto.AccountDto;
import com.Notes.entity.Account;
import com.Notes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaAccountService implements UserDetailsService, AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JpaAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        Optional<Account> account = accountRepository.findByEmail(email);
        if(account.isPresent()){
            return account.get();
        }
        else {
            throw new UsernameNotFoundException("Account not found");
        }
    }

    @Override
    public boolean createAccount(AccountDto accountDto){
        if(emailExists(accountDto.getEmail())){
            return false;
        }
        String hashedPassword = passwordEncoder.encode(accountDto.getPassword());
        Account account = new Account(accountDto.getEmail(), hashedPassword);
        accountRepository.save(account);
        return true;
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


    private boolean emailExists(String email){
        return accountRepository.findByEmail(email).isPresent();
    }
}
