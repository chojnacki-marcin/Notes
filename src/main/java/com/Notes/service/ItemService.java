package com.Notes.service;


import com.Notes.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {

    List<Item> getItemsFromNote(long noteId);

    Optional<Item> getItem(long itemId);
}
