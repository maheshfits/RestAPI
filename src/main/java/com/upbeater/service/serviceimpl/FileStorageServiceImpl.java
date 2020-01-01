package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.User;
import com.upbeater.model.auxiliary.FileStorageProperties;
import com.upbeater.repository.UserRepository;
import com.upbeater.service.FileStorageService;
import com.upbeater.utility.FileStorageException;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStorageLocation;
    private final UserRepository userRepository;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties,
                                  UserRepository userRepository) {
        String folderName = "UserImages";
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + folderName)
                .toAbsolutePath().normalize();
        this.userRepository = userRepository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String uploadUserImage(MultipartFile userImage, int userId) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(userImage.getOriginalFilename()));
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS-");
        String uploadedDate = sdf.format(date);
        String uploadDateAndFileName = uploadedDate + fileName;
        try {
            if (userId > 0) {
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + uploadDateAndFileName);
                }
                Path targetLocation = this.fileStorageLocation.resolve(uploadDateAndFileName);
                Files.copy(userImage.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                User findUserDetails = userRepository.findUsersByIdAndStatusNot(userId, StatusTypes.DEACTIVATE.getStatusId());
                findUserDetails.setImageUrl(uploadDateAndFileName);
                this.userRepository.save(findUserDetails);
                return uploadDateAndFileName;
            } else {
                return null;
            }
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + uploadDateAndFileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String imageName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(imageName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException ex) {
            return null;
        }
    }
}
