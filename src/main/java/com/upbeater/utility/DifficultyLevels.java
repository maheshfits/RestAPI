package com.upbeater.utility;

public enum DifficultyLevels {
    EASY("Easy", 1),
    INTERMEDIATE("Intermediate", 2),
    HARD("Hard", 3),
    MASTER("Master", 4);

    private String difficultyLevel;
    private Integer difficultyId;

    DifficultyLevels(String difficultyLevel, int difficultyId) {
        this.difficultyLevel = difficultyLevel;
        this.difficultyId = difficultyId;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }
}
