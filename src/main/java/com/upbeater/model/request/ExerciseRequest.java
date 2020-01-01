package com.upbeater.model.request;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseRequest implements Serializable {

    private int taskId;
    private int difficultyLevel;
    private String topic;
    private String description;
    private Integer status;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRequest that = (ExerciseRequest) o;
        return taskId == that.taskId &&
                difficultyLevel == that.difficultyLevel &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, difficultyLevel, topic, description, status);
    }
}
