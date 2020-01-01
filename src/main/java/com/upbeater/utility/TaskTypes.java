package com.upbeater.utility;

public enum TaskTypes {
    PDF("Pdf", 1),
    VIDEO("Video", 2),
    QUIZ("Quiz", 3),
    EXERCISE("Video", 4);

    private String taskType;
    private Integer taskId;

    TaskTypes(String taskType, int taskId) {
        this.taskType = taskType;
        this.taskId = taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
