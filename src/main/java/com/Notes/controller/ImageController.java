package com.Notes.controller;

import com.Notes.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/images/{accountId}/{imageName}")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getImage(@PathVariable long accountId, @PathVariable String imageName) throws IOException {
        byte[] image = imageService.getImageContents(accountId, imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

}
