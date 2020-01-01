package com.upbeater.service;

import com.upbeater.model.request.ExerciseRequest;
import com.upbeater.model.response.ExerciseResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExerciseService {
    ResponseEntity<ResponseObject> createExercise(ExerciseRequest exerciseRequest);

    List<ExerciseResponse> getExercisesByTaskId(int taskId);

//    List<ChapterResponse> getChaptersByMaterialId(int materialId);
}
