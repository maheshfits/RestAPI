package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exercise")
public class Exercise implements Serializable {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int exerciseId;
    @Column(name = "task_id")
    private int taskId;
    @Column(name = "difficulty_level")
    private int difficultyLevel;
    @Column(name = "topic")
    private String topic;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;

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
        Exercise exercise = (Exercise) o;
        return exerciseId == exercise.exerciseId &&
                taskId == exercise.taskId &&
                difficultyLevel == exercise.difficultyLevel &&
                Objects.equals(topic, exercise.topic) &&
                Objects.equals(description, exercise.description) &&
                Objects.equals(status, exercise.status) &&
                Objects.equals(createdDate, exercise.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, taskId, difficultyLevel, topic, description, status, createdDate);
    }
}
