package com.Notes.service;


import com.Notes.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {

    List<Item> getItemsFromNote(long noteId);

    Optional<Item> getItem(long itemId);

    Optional<Item> addItemToNote(long noteId, Item item);

    boolean modifyItem(long itemId, Item newItem);

    boolean deleteItem(long itemId);
}
