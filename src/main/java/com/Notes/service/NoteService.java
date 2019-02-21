package com.Notes.service;

import com.Notes.entity.Account;
import com.Notes.entity.Image;
import com.Notes.entity.Note;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note createNote(Note note, Account account);

    Optional<Image> addImageToNote(long noteId, String imageTitle, MultipartFile image, long accountId);

    boolean updateNote(long noteId, Note newNote);

    Optional<Note> getNote(long noteId);

    boolean deleteNote(long noteId);
}
