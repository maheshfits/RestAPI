package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    @Column(name = "chapter_id")
    private int chapterId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                chapterId == task.chapterId &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(status, task.status) &&
                Objects.equals(createdDate, task.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, chapterId, taskName, status, createdDate);
    }
}
