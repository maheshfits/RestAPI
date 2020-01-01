package com.upbeater.controller;

import com.upbeater.model.dom.TaskResource;
import com.upbeater.model.auxiliary.UploadFileResponse;
import com.upbeater.model.response.TaskResourceResponse;
import com.upbeater.repository.TaskResourceRepository;
import com.upbeater.service.TaskFilesStorageService;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/taskResourcesController")
public class TaskResourcesController {
    private final String APPLICATION_JSON = "application/json";

    @Value("${file.upload-dir}")
    private String resourceUploadPath;

    private final TaskFilesStorageService taskFilesStorageService;
    private final TaskResourceRepository taskResourceRepository;

    @Autowired
    public TaskResourcesController(TaskFilesStorageService taskFilesStorageService,
                                   TaskResourceRepository taskResourceRepository) {
        this.taskFilesStorageService = taskFilesStorageService;
        this.taskResourceRepository = taskResourceRepository;
    }

    @RequestMapping(value = "/createTaskResource", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public UploadFileResponse createTaskResource(@RequestParam("resourceFile") MultipartFile resourceFile, @RequestParam("taskId") int taskId, @RequestParam("taskTypeId") int taskTypeId, @RequestParam("description") String description, @RequestParam("status") int status) {
        String folderName = "TaskResources";
        File file = new File(resourceUploadPath + folderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                resourceUploadPath = resourceUploadPath + folderName;
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        String fileName = taskFilesStorageService.uploadTaskResources(resourceFile, taskId, taskTypeId, description, status);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(resourceUploadPath)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                resourceFile.getContentType(), resourceFile.getSize());
    }

    @GetMapping("/viewTaskResource/{taskResourceId}")
    public ResponseEntity<Resource> viewTaskResource(@PathVariable int taskResourceId, HttpServletRequest request) {
        TaskResource taskResource = taskResourceRepository.findTaskResourceByResourceIdAndStatusNot(taskResourceId, StatusTypes.DEACTIVATE.getStatusId());
        String contentType = null;
        if (taskResource != null) {
            Resource resource = taskFilesStorageService.loadFileAsResource(taskResource.getResourceFile());
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                ex.getStackTrace();
            }

            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + null + "\"")
                    .body(null);
        }
    }

    @RequestMapping(value = "/getAllTaskResources/{taskId}/{taskTypeId}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<TaskResourceResponse> getAllTaskResources(@PathVariable(value = "taskId") int taskId, @PathVariable(value = "taskTypeId") int taskTypeId) {
        return taskFilesStorageService.getAllTaskResources(taskId, taskTypeId);
    }
}

