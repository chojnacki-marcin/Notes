package com.Notes.controller;

import com.Notes.entity.Image;
import com.Notes.entity.Note;
import com.Notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{accountId}/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    public List<Note> getUserNotes(@PathVariable long accountId){
        return noteService.getUserNotes(accountId);
    }


    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable long noteId){
        Optional<Note> note = noteService.getNote(noteId);
        if(note.isPresent()){
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Valid Note note, @PathVariable long accountId){
        Note created = noteService.createNote(note, accountId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PostMapping("/{noteId}/images")
    public ResponseEntity<Note> addImageToNote(@PathVariable long noteId, @RequestParam("title") String imageTitle, @RequestParam("image") MultipartFile image){
        Note note = noteService.addImageToNote(noteId, imageTitle, image);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
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
