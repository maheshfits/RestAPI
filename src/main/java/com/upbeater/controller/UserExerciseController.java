package com.upbeater.controller;

import com.upbeater.model.auxiliary.UploadFileResponse;
import com.upbeater.model.dom.User;
import com.upbeater.model.response.UserExerciseResponse;
import com.upbeater.repository.UserRepository;
import com.upbeater.service.UserExerciseService;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

@RestController
@RequestMapping(value = "/rest/userExerciseController")
public class UserExerciseController {
    private final String APPLICATION_JSON = "application/json";

    @Value("${file.upload-dir}")
    private String resourceUploadPath;

    private final UserRepository userRepository;
    private final UserExerciseService userExerciseService;

    @Autowired
    public UserExerciseController(UserRepository userRepository, UserExerciseService userExerciseService) {
        this.userRepository = userRepository;
        this.userExerciseService = userExerciseService;
    }

    @RequestMapping(value = "/createUserExercise", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public UploadFileResponse createTaskResource(@RequestParam("resourceFile") MultipartFile resourceFile, @RequestParam("exerciseId") int exerciseId, @RequestParam("userId") int userId, @RequestParam("thinkOfTask") int thinkOfTask, @RequestParam("feedback") String feedback) {
        String fileName = null;
        String fileDownloadUri = null;

        User userDetails = this.userRepository.findUsersByIdAndStatusNot(userId, StatusTypes.DEACTIVATE.getStatusId());
        if (userDetails != null) {
            String folderName = "UserResources";
            File file = new File(resourceUploadPath + folderName);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            File userFolder = new File(resourceUploadPath + folderName + "/" + userDetails.getId());
            if (!userFolder.exists()) {
                if (userFolder.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            if (userFolder.exists() == (resourceUploadPath + folderName + "/" + userDetails.getId() != null)) {
                System.out.println("Operation..");
                fileName = userExerciseService.createUserExercise(resourceFile, exerciseId, userDetails.getId(), thinkOfTask, feedback);
                fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(resourceUploadPath)
                        .path(fileName)
                        .toUriString();
            } else {
                return null;
            }
        } else {
            return null;
        }

        return new UploadFileResponse(fileName, fileDownloadUri,
                resourceFile.getContentType(), resourceFile.getSize());
    }

    @RequestMapping(value = "/getUserExercise/{userId}/{exerciseId}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public UserExerciseResponse getUserExercise(@PathVariable(value = "userId") int userId, @PathVariable(value = "exerciseId") int exerciseId) {
        return userExerciseService.getUserExercise(userId, exerciseId);
    }
}
