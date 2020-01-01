package com.upbeater.repository;

import com.upbeater.model.dom.TaskResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskResourceRepository extends JpaRepository<TaskResource, Integer> {
    TaskResource findTaskResourceByResourceIdAndStatusNot(int taskResourceId, int status);

    List<TaskResource> findTaskResourceByTaskIdAndTaskTypeIdAndStatusNot(int taskId, int taskTypeId, int status);
}
