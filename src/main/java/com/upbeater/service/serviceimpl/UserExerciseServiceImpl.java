package com.upbeater.service.serviceimpl;

import com.upbeater.model.auxiliary.FileStorageProperties;
import com.upbeater.model.dom.Exercise;
import com.upbeater.model.dom.UserExercises;
import com.upbeater.model.response.UserExerciseResponse;
import com.upbeater.repository.ExerciseRepository;
import com.upbeater.repository.UserExerciseRepository;
import com.upbeater.service.UserExerciseService;
import com.upbeater.utility.FileStorageException;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UserExerciseServiceImpl implements UserExerciseService {
    private Path fileStorageLocation;

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;
    private final FileStorageProperties fileStorageProperties;
    private final static String folderName = "UserResources/";

    @Autowired
    public UserExerciseServiceImpl(ExerciseRepository exerciseRepository,
                                   UserExerciseRepository userExerciseRepository,
                                   FileStorageProperties fileStorageProperties) {
        this.exerciseRepository = exerciseRepository;
        this.userExerciseRepository = userExerciseRepository;
        this.fileStorageProperties = fileStorageProperties;

    }

    @Override
    public String createUserExercise(MultipartFile resourceFile, int exerciseId, int userId, int thinkOfTask, String feedback) {
        Exercise exercise = exerciseRepository.findExercisesByExerciseIdAndStatusNot(exerciseId, StatusTypes.DEACTIVATE.getStatusId());
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(resourceFile.getOriginalFilename()));
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS-");
        String uploadedDate = sdf.format(date);
        String uploadDateAndFileName = uploadedDate + exercise.getTopic() + "-" + fileName;
        try {
            if (userId > 0) {
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + uploadDateAndFileName);
                }
                this.fileStorageLocation = Paths.get(this.fileStorageProperties.getUploadDir() + folderName + userId)
                        .toAbsolutePath().normalize();
                Path targetLocation = this.fileStorageLocation.resolve(uploadDateAndFileName);
                Files.copy(resourceFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                UserExercises userExercises = new UserExercises();
                userExercises.setExerciseId(exerciseId);
                userExercises.setCompleted(true);
                userExercises.setFeedback(feedback);
                userExercises.setReportUrl(uploadDateAndFileName);
                userExercises.setCreatedDate(new Date());
                userExercises.setStatus(StatusTypes.ACTIVE.getStatusId());
                userExercises.setUserId(userId);
                userExercises.setThinkOfTask(thinkOfTask);

                this.userExerciseRepository.save(userExercises);
                return uploadDateAndFileName;
            } else {
                return null;
            }
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + uploadDateAndFileName + ". Please try again!", ex);
        }
    }

    @Override
    public UserExerciseResponse getUserExercise(int userId, int exerciseId) {
        UserExercises userExercise = userExerciseRepository.findUserExercisesByUserIdAndExerciseIdAndStatusNot(userId, exerciseId, StatusTypes.DEACTIVATE.getStatusId());
        if (userExercise != null) {
            UserExerciseResponse userExerciseResponse = new UserExerciseResponse();
            userExerciseResponse.setUserId(userExercise.getUserId());
            userExerciseResponse.setExerciseId(userExercise.getExerciseId());
            userExerciseResponse.setUserExerciseId(userExercise.getUserExerciseId());
            userExerciseResponse.setCompleted(userExercise.isCompleted());
            userExerciseResponse.setThinkOfTask(userExercise.getThinkOfTask());
            userExerciseResponse.setReportUrl(userExercise.getReportUrl());
            userExerciseResponse.setFeedback(userExercise.getFeedback());

            return userExerciseResponse;
        } else {
            return null;
        }
    }
}
