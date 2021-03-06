package com.Notes.service;

import com.Notes.entity.Account;
import com.Notes.entity.Image;
import com.Notes.entity.Note;
import com.Notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class JpaNoteService implements NoteService {

    private final NoteRepository noteRepository;
    private final ImageService imageService;


    @Autowired
    public JpaNoteService(NoteRepository noteRepository,ImageService imageService) {
        this.noteRepository = noteRepository;
        this.imageService = imageService;
    }


    @Override
    public Note createNote(Note note, Account account) {
        note.setOwner(account);
        return noteRepository.save(note);
    }

    @Override
    public Optional<Image> addImageToNote(long noteId, String imageTitle, MultipartFile file, long accountId) {
        if (noteRepository.findById(noteId).isPresent()) {
            Note note = noteRepository.findById(noteId).get();
            Image image = imageService.saveImage(file, imageTitle, note, accountId);
            note.getImages().add(image);
            noteRepository.save(note);
            return Optional.of(image);

        } else {
            return Optional.empty();
        }

    }

    @Override
    public boolean updateNote(long noteId, Note newNote) {
        if(noteRepository.findById(noteId).isPresent()){
            newNote.setNoteId(noteId);
            noteRepository.save(newNote);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Optional<Note> getNote(long noteId) {
        return noteRepository.findById(noteId);
    }

    @Override
    public boolean deleteNote(long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        if(noteOptional.isPresent()){
            Note note = noteOptional.get();
            note.getImages().forEach(imageService::deleteImage);
            noteRepository.delete(note);
            return true;
        }
        else {
            return false;
        }

    }


}
