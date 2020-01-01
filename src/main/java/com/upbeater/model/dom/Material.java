package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "material")
public class Material implements Serializable {
    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "material_topic")
    private String materialTopic;
    @Column(name = "material_description")
    private String materialDescription;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialTopic() {
        return materialTopic;
    }

    public void setMaterialTopic(String materialTopic) {
        this.materialTopic = materialTopic;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
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
        Material that = (Material) o;
        return id == that.id &&
                Objects.equals(materialTopic, that.materialTopic) &&
                Objects.equals(materialDescription, that.materialDescription) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialTopic, materialDescription, status, createdDate);
    }
}
