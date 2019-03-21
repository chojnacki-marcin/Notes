package com.Notes.controller;

import com.Notes.entity.Item;
import com.Notes.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public List<Item> getItemsFromNote(@PathVariable long noteId){
        return itemService.getItemsFromNote(noteId);
    }

    @GetMapping("/{itemId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity<Item> getItem(@PathVariable long itemId, @PathVariable long noteId){
        Optional<Item> item = itemService.getItem(itemId);
        return ResponseEntity.of(item);
    }

    @PostMapping
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity<Item> addItem(@PathVariable long noteId, @RequestBody @Valid Item item){
        Optional<Item> createdItem = itemService.addItemToNote(noteId, item);
        return ResponseEntity.of(createdItem);
    }



    @PutMapping("/{itemId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity modifyItem(@PathVariable long itemId, @RequestBody @Valid Item item, @PathVariable long noteId){
        if(itemService.modifyItem(itemId, item)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity deleteItem(@PathVariable long itemId, @PathVariable long noteId){
        if(itemService.deleteItem(itemId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
