package com.kalanso.event.Service;

import com.kalanso.event.Model.FileData;
import com.kalanso.event.Model.ImageData;
import com.kalanso.event.Model.ImageUtils;
import com.kalanso.event.Repository.FileDataRepository;
import com.kalanso.event.Repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageService {

    private final StorageRepository repository;
    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH = "C:\\Users\\saran.soumbounou\\Desktop\\MyFIles\\";

    public ImageData uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();

        return repository.save(imageData);
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        return new byte[0]; // or handle as appropriate
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        FileData fileData = FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();

        fileDataRepository.save(fileData);
        file.transferTo(new File(filePath));

        return "file uploaded successfully: " + filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());
        }
        return new byte[0]; // or handle as appropriate
    }
}
