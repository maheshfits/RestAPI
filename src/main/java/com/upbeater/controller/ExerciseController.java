package com.upbeater.controller;

import com.upbeater.model.request.ExerciseRequest;
import com.upbeater.model.response.ExerciseResponse;
import com.upbeater.service.ExerciseService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/exerciseController")
public class ExerciseController {
    private final String APPLICATION_JSON = "application/json";

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @RequestMapping(value = "/createExercise", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createExercise(@RequestBody ExerciseRequest exerciseRequest) {
        return this.exerciseService.createExercise(exerciseRequest);
    }

    @RequestMapping(value = "/getExercisesByTaskId/{taskId}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<ExerciseResponse> getExercisesByTaskId(@PathVariable(value = "taskId") int taskId) {
        return exerciseService.getExercisesByTaskId(taskId);
    }
}
