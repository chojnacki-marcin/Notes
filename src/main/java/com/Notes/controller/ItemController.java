package com.Notes.controller;

import com.Notes.entity.Item;
import com.Notes.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes/{noteId}/items")
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

    @PostMapping
    public ResponseEntity<Item> addItem(@PathVariable long noteId, @RequestBody @Valid Item item){
        Optional<Item> createdItem = itemService.addItemToNote(noteId, item);
        return ResponseEntity.of(createdItem);
    }



    @PutMapping("/{itemId}")
    public ResponseEntity modifyItem(@PathVariable long itemId, @RequestBody @Valid Item item){
        if(itemService.modifyItem(itemId, item)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable long itemId){
        if(itemService.deleteItem(itemId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
