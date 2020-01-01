package com.upbeater.service;

import com.upbeater.model.response.TaskResourceResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskFilesStorageService {
    Resource loadFileAsResource(String resourceFileName);

    String uploadTaskResources(MultipartFile resourceFile, int taskId, int taskTypeId, String description, int status);

    List<TaskResourceResponse> getAllTaskResources(int taskId, int taskTypeId);
}
