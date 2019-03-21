package com.Notes.controller;

import com.Notes.entity.Account;
import com.Notes.entity.Image;
import com.Notes.entity.Note;
import com.Notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }





    @GetMapping("/{noteId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity<Note> getNote(@PathVariable long noteId){
        Optional<Note> note = noteService.getNote(noteId);
        return note.map(n -> new ResponseEntity<>(n, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Valid Note note, @AuthenticationPrincipal Account account){
        Note created = noteService.createNote(note, account);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }




    @PutMapping("/{noteId}")
    public ResponseEntity modifyNote(@RequestBody @Valid Note newNote, @PathVariable long noteId){
        if(noteService.updateNote(noteId, newNote)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity deleteNote(@PathVariable long noteId){
        if(noteService.deleteNote(noteId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
