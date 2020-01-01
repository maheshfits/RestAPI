package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.TaskResource;
import com.upbeater.model.auxiliary.FileStorageProperties;
import com.upbeater.model.response.TaskResourceResponse;
import com.upbeater.repository.TaskResourceRepository;
import com.upbeater.repository.UserRepository;
import com.upbeater.service.TaskFilesStorageService;
import com.upbeater.utility.FileStorageException;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TaskFilesStorageServiceImpl implements TaskFilesStorageService {
    private Path fileStorageLocation;
    private final UserRepository userRepository;
    private final TaskResourceRepository taskResourceRepository;

    @Autowired
    public TaskFilesStorageServiceImpl(FileStorageProperties fileStorageProperties,
                                       UserRepository userRepository,
                                       TaskResourceRepository taskResourceRepository) {
        this.taskResourceRepository = taskResourceRepository;
        String folderName = "TaskResources";
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + folderName)
                .toAbsolutePath().normalize();
        this.userRepository = userRepository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String resourceFileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(resourceFileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    @Override
    public String uploadTaskResources(MultipartFile resourceFile, int taskId, int taskTypeId, String description, int status) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(resourceFile.getOriginalFilename()));
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS-");
        String uploadedDate = sdf.format(date);
        String uploadDateAndFileName = uploadedDate + fileName;
        try {
            if (taskId > 0 && taskTypeId > 0) {
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + uploadDateAndFileName);
                }
                Path targetLocation = this.fileStorageLocation.resolve(uploadDateAndFileName);
                Files.copy(resourceFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                TaskResource taskResource = new TaskResource();
                taskResource.setTaskTypeId(taskTypeId);
                taskResource.setTaskId(taskId);
                taskResource.setStatus(status);
                taskResource.setDescription(description);
                taskResource.setCreatedDate(new Date());
                taskResource.setResourceFile(uploadDateAndFileName);
                this.taskResourceRepository.save(taskResource);
                return uploadDateAndFileName;
            } else {
                return null;
            }
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + uploadDateAndFileName + ". Please try again!", ex);
        }
    }

    @Override
    public List<TaskResourceResponse> getAllTaskResources(int taskId, int taskTypeId) {
        List<TaskResourceResponse> responseList = new ArrayList<>();
        if (taskId > 0 && taskTypeId > 0) {
            List<TaskResource> taskResourcesList = this.taskResourceRepository.findTaskResourceByTaskIdAndTaskTypeIdAndStatusNot(taskId, taskTypeId, StatusTypes.DEACTIVATE.getStatusId());
            if (taskResourcesList != null) {
                for (TaskResource taskResource : taskResourcesList) {
                    TaskResourceResponse taskResourceResponse = new TaskResourceResponse();
                    taskResourceResponse.setResourceId(taskResource.getResourceId());
                    taskResourceResponse.setDescription(taskResource.getDescription());
                    taskResourceResponse.setResourceFile(taskResource.getResourceFile());
                    responseList.add(taskResourceResponse);
                }
                return responseList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
