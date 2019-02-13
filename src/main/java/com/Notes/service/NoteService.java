package com.Notes.service;

import com.Notes.entity.Image;
import com.Notes.entity.Note;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getUserNotes(long accountId);

    Note createNote(Note note, long accountId);

    Optional<Image> addImageToNote(long noteId, String imageTitle, MultipartFile image);

    boolean updateNote(long noteId, Note newNote);

    Optional<Note> getNote(long noteId);

    boolean deleteNote(long noteId);
}
