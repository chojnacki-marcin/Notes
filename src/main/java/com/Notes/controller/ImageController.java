package com.Notes.controller;

import com.Notes.entity.Account;
import com.Notes.entity.Image;
import com.Notes.entity.Note;
import com.Notes.service.ImageService;
import com.Notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/notes/{noteId}/images")
public class ImageController {

    private final ImageService imageService;
    private final NoteService noteService;

    @Autowired
    public ImageController(ImageService imageService, NoteService noteService) {
        this.imageService = imageService;
        this.noteService = noteService;
    }

    @GetMapping("/{imageId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity<Image> getImage(@PathVariable long imageId, @PathVariable long noteId) {
        Optional<Image> image = imageService.getImage(imageId);
        if (image.isPresent()) {
            return new ResponseEntity<>(image.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{imageId}")
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity deleteImage(@PathVariable long imageId, @PathVariable long noteId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @PreAuthorize("@securityService.isOwner(authentication, #noteId)")
    public ResponseEntity<Image> addImageToNote(@PathVariable long noteId, @RequestParam("title") String imageTitle,
                                                @RequestParam("image") MultipartFile image, @AuthenticationPrincipal Account account) {
        Optional<Image> note = noteService.addImageToNote(noteId, imageTitle, image, account.getAccountId());
        return ResponseEntity.of(note);
    }
}
