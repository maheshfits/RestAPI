package com.upbeater.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uploadUserImage(MultipartFile userImage, int userId);

    Resource loadFileAsResource(String imageName);

}
