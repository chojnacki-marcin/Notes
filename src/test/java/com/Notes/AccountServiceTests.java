package com.Notes;

import com.Notes.dto.AccountDto;
import com.Notes.entity.Account;
import com.Notes.repository.AccountRepository;
import com.Notes.service.AccountService;
import com.Notes.service.JpaAccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AccountService accountService;

    @Before
    public void setup(){
        accountService = new JpaAccountService(accountRepository, passwordEncoder);
    }

    @Test
    public void shouldReturnFalseWhenAccountWithGivenEmailAlreadyExists(){
        Account dummyAccount = new Account();
        String testedEmail = "email@email.pl";
        Mockito.when(accountRepository.findByEmail(testedEmail)).thenReturn(Optional.of(dummyAccount));

        AccountDto newAccount = new AccountDto();
        newAccount.setEmail(testedEmail);

        assertFalse(accountService.createAccount(newAccount));
    }


    @Test
    public void shouldReturnTrueWhenCreationIsSuccessful(){

        AccountDto newAccount = new AccountDto("email@email.pl", "Password123");
        assertTrue(accountService.createAccount(newAccount));
    }

    @Test
    public void shouldReturnFalseWhenAccountIsNull(){
        assertFalse(accountService.createAccount(null));
    }



}

