package com.upbeater.repository;

import com.upbeater.model.dom.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTaskByChapterIdAndStatusNot(int chapterId, int status);

    Task findTaskByTaskIdAndStatusNot(int taskId, int status);
}
