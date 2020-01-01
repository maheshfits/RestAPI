package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_exercises")
public class UserExercises implements Serializable {

    @Id
    @Column(name = "user_exercise_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int UserExerciseId;
    @Column(name = "exercise_id")
    private int exerciseId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "report_url")
    private String reportUrl;
    @Column(name = "think_of_task")
    private int thinkOfTask;
    @Column(name = "is_completed")
    private boolean isCompleted;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;

    public int getUserExerciseId() {
        return UserExerciseId;
    }

    public void setUserExerciseId(int userExerciseId) {
        UserExerciseId = userExerciseId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public int getThinkOfTask() {
        return thinkOfTask;
    }

    public void setThinkOfTask(int thinkOfTask) {
        this.thinkOfTask = thinkOfTask;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
        UserExercises that = (UserExercises) o;
        return UserExerciseId == that.UserExerciseId &&
                exerciseId == that.exerciseId &&
                userId == that.userId &&
                thinkOfTask == that.thinkOfTask &&
                isCompleted == that.isCompleted &&
                Objects.equals(reportUrl, that.reportUrl) &&
                Objects.equals(feedback, that.feedback) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserExerciseId, exerciseId, userId, reportUrl, thinkOfTask, isCompleted, feedback, status, createdDate);
    }
}
