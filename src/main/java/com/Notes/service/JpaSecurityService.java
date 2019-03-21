package com.Notes.service;

import com.Notes.entity.Account;
import com.Notes.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("securityService")
public class JpaSecurityService implements SecurityService{

    private final NoteService noteService;
    private final AccountService accountService;

    @Autowired
    public JpaSecurityService(NoteService noteService, AccountService accountService) {
        this.noteService = noteService;
        this.accountService = accountService;
    }

    @Override
    public boolean isOwner(Authentication authentication, long noteID) {
        Optional<Account> account = accountService.findAccountByEmail(authentication.getName());
        Optional<Note> note = noteService.getNote(noteID);
        return account.isPresent() &&  note.isPresent() && note.get().getOwner().getAccountId() == account.get().getAccountId();
    }
}
