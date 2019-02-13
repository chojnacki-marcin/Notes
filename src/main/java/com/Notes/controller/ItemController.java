package com.Notes.controller;

import com.Notes.entity.Item;
import com.Notes.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{accountId}/notes/{noteId}/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItemsFromNote(@PathVariable long noteId){
        return itemService.getItemsFromNote(noteId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getNote(@PathVariable long itemId){
        Optional<Item> item = itemService.getItem(itemId);
        return ResponseEntity.of(item);
    }
}
