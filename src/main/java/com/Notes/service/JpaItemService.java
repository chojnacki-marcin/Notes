package com.Notes.service;

import com.Notes.entity.Item;
import com.Notes.entity.Note;
import com.Notes.repository.ItemRepository;
import com.Notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaItemService implements ItemService {
    private final ItemRepository itemRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public JpaItemService(ItemRepository itemRepository, NoteRepository noteRepository) {
        this.itemRepository = itemRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Item> getItemsFromNote(long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        return note.map(Note::getItems).orElse(new ArrayList<>());
    }

    @Override
    public Optional<Item> getItem(long itemId) {
        return itemRepository.findById(itemId);
    }
}
