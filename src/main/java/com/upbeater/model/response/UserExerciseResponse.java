package com.upbeater.model.response;

import java.io.Serializable;

public class UserExerciseResponse implements Serializable {

    private int UserExerciseId;
    private int exerciseId;
    private int userId;
    private String reportUrl;
    private int thinkOfTask;
    private boolean isCompleted;
    private String feedback;

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
}
