package com.upbeater.model.request;

import java.io.Serializable;
import java.util.Objects;

public class ChapterRequest implements Serializable {

    private int materialId;
    private String chapterName;
    private String chapterDescription;
    private Integer status;

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
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
        ChapterRequest that = (ChapterRequest) o;
        return materialId == that.materialId &&
                Objects.equals(chapterName, that.chapterName) &&
                Objects.equals(chapterDescription, that.chapterDescription) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, chapterName, chapterDescription, status);
    }
}
