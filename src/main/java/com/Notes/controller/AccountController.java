package com.Notes.controller;

import com.Notes.dto.AccountDto;
import com.Notes.entity.Note;
import com.Notes.service.AccountService;
import com.Notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AccountController {

    private final AccountService accountService;
    private final NoteService noteService;

    @Autowired
    public AccountController(AccountService accountService, NoteService noteService) {
        this.accountService = accountService;
        this.noteService = noteService;
    }



    @PostMapping
    public ResponseEntity createAccount(@Valid @RequestBody AccountDto accountDto){
        if(accountService.createAccount(accountDto)){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
