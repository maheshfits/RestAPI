package com.upbeater.service;

import com.upbeater.model.request.TaskRequest;
import com.upbeater.model.response.TaskResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TaskService {
    ResponseEntity<ResponseObject> createTask(TaskRequest taskRequest);

    List<TaskResponse> getTasksByChapterId(int chapterId);
}
