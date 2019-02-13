package com.Notes.service;

import com.Notes.entity.Image;
import com.Notes.entity.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public interface ImageService {
    Image saveImage(MultipartFile image, String imageTitle, Note note);
    Optional<byte[]> getImageContents(long accountId, String imageName) throws IOException;
    void deleteImage(Image i);
    void deleteImage(long imageId);

    Optional<Image> getImage(long imageId);
}
