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

}
