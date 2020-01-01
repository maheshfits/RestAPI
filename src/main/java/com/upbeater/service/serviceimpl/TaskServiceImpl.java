package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.Task;
import com.upbeater.model.request.TaskRequest;
import com.upbeater.model.response.TaskResponse;
import com.upbeater.repository.TaskRepository;
import com.upbeater.service.TaskService;
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
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createTask(TaskRequest taskRequest) {
        ResponseObject responseObject;
        if (taskRequest != null) {
            Task task = new Task();
            task.setChapterId(taskRequest.getChapterId());
            task.setTaskName(taskRequest.getTaskName());
            task.setStatus(taskRequest.getStatus());
            task.setCreatedDate(new Date());

            Task savedTask = this.taskRepository.save(task);
            responseObject = new ResponseObject("Task Saved Successfully!", true, savedTask);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Task Saving Error!", true, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<TaskResponse> getTasksByChapterId(int chapterId) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        if (chapterId > 0) {
            List<Task> findTaskList = this.taskRepository.findTaskByChapterIdAndStatusNot(chapterId, StatusTypes.DEACTIVATE.getStatusId());
            if (findTaskList != null) {
                for (Task task : findTaskList) {
                    TaskResponse taskResponse = new TaskResponse();
                    taskResponse.setTaskId(task.getTaskId());
                    taskResponse.setTaskName(task.getTaskName());
                    taskResponse.setChapterId(task.getChapterId());
                    taskResponse.setStatus(task.getStatus());
                    taskResponseList.add(taskResponse);
                }
                return taskResponseList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
