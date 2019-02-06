package com.Notes.controller;

import com.Notes.entity.Note;
import com.Notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    public List<Note> getUserNotes(long accountId){
        return noteService.getUserNotes(accountId);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Valid Note note){
        Note created = noteService.createNote(note);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
