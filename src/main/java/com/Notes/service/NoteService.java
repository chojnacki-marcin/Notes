package com.Notes.service;

import com.Notes.entity.Note;
import com.Notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getUserNotes(long accountId){
        return noteRepository.findAllByAccountId(accountId);
    }

    public Note createNote(Note note){
        return noteRepository.save(note);
    }
}
