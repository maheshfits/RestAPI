package com.upbeater.model.response;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseResponse implements Serializable {

    private int exerciseId;
    private int taskId;
    private int difficultyLevel;
    private String topic;
    private String description;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseResponse that = (ExerciseResponse) o;
        return exerciseId == that.exerciseId &&
                taskId == that.taskId &&
                difficultyLevel == that.difficultyLevel &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, taskId, difficultyLevel, topic, description);
    }
}
