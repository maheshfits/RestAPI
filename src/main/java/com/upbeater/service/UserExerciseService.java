package com.upbeater.service;

import com.upbeater.model.response.UserExerciseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserExerciseService {

    String createUserExercise(MultipartFile resourceFile, int exerciseId, int userId, int thinkOfTask, String feedback);

    UserExerciseResponse getUserExercise(int userId, int exerciseId);
}
