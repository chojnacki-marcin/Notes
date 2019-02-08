package com.Notes.service;

import com.Notes.entity.Image;
import com.Notes.entity.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {
    Image saveImage(MultipartFile image, String imageTitle, Note note);
    byte[] getImageContents(long accountId, String imageName) throws IOException;
}
