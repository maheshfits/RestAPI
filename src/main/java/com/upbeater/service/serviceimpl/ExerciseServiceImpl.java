package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.Exercise;
import com.upbeater.model.dom.Task;
import com.upbeater.model.request.ExerciseRequest;
import com.upbeater.model.response.ExerciseResponse;
import com.upbeater.repository.ExerciseRepository;
import com.upbeater.repository.TaskRepository;
import com.upbeater.service.ExerciseService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository,
                               TaskRepository taskRepository) {
        this.exerciseRepository = exerciseRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createExercise(ExerciseRequest exerciseRequest) {
        ResponseObject responseObject;
        if (exerciseRequest != null) {
            Exercise exercise = new Exercise();
            exercise.setTaskId(exerciseRequest.getTaskId());
            exercise.setDifficultyLevel(exerciseRequest.getDifficultyLevel());
            exercise.setDescription(exerciseRequest.getDescription());
            exercise.setTopic(exerciseRequest.getTopic());
            exercise.setStatus(exerciseRequest.getStatus());
            exercise.setCreatedDate(new Date());
            Exercise savedExercise = this.exerciseRepository.save(exercise);
            responseObject = new ResponseObject("Exercise Successfully Saved!", true, savedExercise);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Exercise Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ExerciseResponse> getExercisesByTaskId(int taskId) {
        List<Exercise> exerciseList;
        List<ExerciseResponse> exerciseResponseList = new ArrayList<>();
        if (taskId > 0) {
            Task findTask = taskRepository.findTaskByTaskIdAndStatusNot(taskId, StatusTypes.DEACTIVATE.getStatusId());
            if (findTask != null) {
                exerciseList = exerciseRepository.findExercisesByTaskIdAndStatusNot(findTask.getTaskId(), StatusTypes.DEACTIVATE.getStatusId());
                for (Exercise exercise : exerciseList) {
                    ExerciseResponse exerciseResponse = new ExerciseResponse();
                    exerciseResponse.setTaskId(exercise.getTaskId());
                    exerciseResponse.setExerciseId(exercise.getExerciseId());
                    exerciseResponse.setDifficultyLevel(exercise.getDifficultyLevel());
                    exerciseResponse.setTopic(exercise.getTopic());
                    exerciseResponse.setDescription(exercise.getDescription());
                    exerciseResponseList.add(exerciseResponse);
                }
                return exerciseResponseList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
