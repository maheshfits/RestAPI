package com.upbeater.model.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_material")
public class UserMaterial implements Serializable {
    @Id
    @Column(name = "user_material_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "material_id")
    private int materialId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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
        UserMaterial that = (UserMaterial) o;
        return id == that.id &&
                userId == that.userId &&
                materialId == that.materialId &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdDate, userId, materialId);
    }
}
