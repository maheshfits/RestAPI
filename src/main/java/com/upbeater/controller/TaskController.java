package com.upbeater.controller;

import com.upbeater.model.request.TaskRequest;
import com.upbeater.model.response.TaskResponse;
import com.upbeater.service.TaskService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/taskController")
public class TaskController {
    private final String APPLICATION_JSON = "application/json";

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/createTask", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @RequestMapping(value = "/getTasksByChapterId/{chapterId}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<TaskResponse> getTasksByChapterId(@PathVariable(value = "chapterId") int chapterId) {
        return taskService.getTasksByChapterId(chapterId);
    }
}

