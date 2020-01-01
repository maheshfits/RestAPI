package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "chapter")
public class Chapter implements Serializable {

    @Id
    @Column(name = "chapter_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int chapterId;
    @Column(name = "material_id")
    private int materialId;
    @Column(name = "chapter_name")
    private String chapterName;
    @Column(name = "chapter_description")
    private String chapterDescription;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

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
        Chapter chapter = (Chapter) o;
        return chapterId == chapter.chapterId &&
                materialId == chapter.materialId &&
                Objects.equals(chapterName, chapter.chapterName) &&
                Objects.equals(chapterDescription, chapter.chapterDescription) &&
                Objects.equals(status, chapter.status) &&
                Objects.equals(createdDate, chapter.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapterId, materialId, chapterName, chapterDescription, status, createdDate);
    }
}
