package com.upbeater.controller;

import com.upbeater.model.dom.User;
import com.upbeater.model.auxiliary.ResetPassword;
import com.upbeater.model.auxiliary.UploadFileResponse;
import com.upbeater.model.request.UserRequest;
import com.upbeater.model.response.UserResponse;
import com.upbeater.repository.UserRepository;
import com.upbeater.service.FileStorageService;
import com.upbeater.service.UserService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/userController")
public class UserController {
    private final String APPLICATION_JSON = "application/json";

    private final UserService userService;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Value("${file.upload-dir}")
    private String userImageUploadPath;

    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository,
                          FileStorageService fileStorageService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<UserResponse> getAllUserTypes() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> resetUserPassword(@RequestBody ResetPassword resetPassword) {
        return userService.resetUserPassword(resetPassword);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(value = "id") int id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/uploadUserImage", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public UploadFileResponse uploadUserImage(@RequestParam("userImage") MultipartFile userImage, @RequestParam("userId") int userId) {
        String folderName = "UserImages";
        File file = new File(userImageUploadPath + folderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                userImageUploadPath = userImageUploadPath + folderName;
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        String fileName = fileStorageService.uploadUserImage(userImage, userId);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(userImageUploadPath)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                userImage.getContentType(), userImage.getSize());
    }

    @GetMapping("/viewUserImage/{userId}")
    public ResponseEntity<Resource> viewUserImage(@PathVariable int userId, HttpServletRequest request) {
        User userDetails = userRepository.findUsersByIdAndStatusNot(userId, StatusTypes.DEACTIVATE.getStatusId());
        String contentType = null;
        if (userDetails != null) {
            Resource resource = fileStorageService.loadFileAsResource(userDetails.getImageUrl());
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                ex.getStackTrace();
            }

            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + null + "\"")
                    .body(null);
        }
    }
}
