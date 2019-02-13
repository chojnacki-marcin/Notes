package com.Notes.service;

import com.Notes.configuration.UploadProperties;
import com.Notes.entity.Account;
import com.Notes.entity.Image;
import com.Notes.entity.Note;
import com.Notes.exception.UploadException;
import com.Notes.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FileSystemImageService implements ImageService {

    private final Path uploadPath;
    private final ImageRepository imageRepository;

    private final String IMAGE_PATH_TEMPLATE = "/images/%d/%s";
    private final String[] ALLOWED_MIME_TYPES = {"image/gif", "image/jpeg", "image/png"};
    private final String FILENAME_TEMPLATE = "%d%d";

    @Autowired
    public FileSystemImageService(UploadProperties properties, ImageRepository imageRepository){
        uploadPath = Paths.get(properties.getUploadPath());
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile imageFile, String imageTitle, Note note) {
        String imageName = saveFileOnServer(imageFile, note);
        Image image = new Image(imageTitle, String.format(IMAGE_PATH_TEMPLATE, note.getAccountId(), imageName));
        return imageRepository.save(image);
    }

    private String saveFileOnServer(MultipartFile imageFile, Note note) {
        String filename = String.format(FILENAME_TEMPLATE, note.getNoteId(), note.getImages().size());
        try {
            if (imageFile.isEmpty()) {
                throw new UploadException("Uploaded file is empty");
            }
            if(!isImage(imageFile)){
                throw new UploadException("File is not an image");
            }
            Path path = uploadPath.resolve(Long.toString(note.getAccountId())).resolve(filename);
            File file = new File(path.toString());
            file.getParentFile().mkdirs();
            imageFile.transferTo(file);
            return file.getName();

        } catch (IOException e) {
            throw new UploadException("Unable to store file");
        }
    }

    private boolean isImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        return contentType != null && Arrays.asList(ALLOWED_MIME_TYPES).contains(contentType);
    }

    @Override
    public byte[] getImageContents(long accountId, String imageName) throws IOException {
        Path path = uploadPath.resolve(Long.toString(accountId)).resolve(imageName);
        return Files.readAllBytes(path);
    }

    @Override
    public void deleteImage(Image i) {
        deleteImageFile(i);
        imageRepository.delete(i);
    }


    @Override
    public void deleteImage(long imageId) {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        imageOptional.ifPresent(this::deleteImage);
    }

    @Override
    public Optional<Image> getImage(long imageId) {
        return imageRepository.findById(imageId);
    }


    private void deleteImageFile(Image i) {
        Path filePath = uploadPath.resolve(i.getImagePath());
        File file = new File(filePath.toUri());
        file.delete();
    }


}
