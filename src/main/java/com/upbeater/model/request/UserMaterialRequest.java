package com.upbeater.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMaterialRequest implements Serializable {
    private int id;
    private Integer status;
    private int userId;
//    private int materialId;
    private List<MaterialRequest> materialList = new ArrayList<>();

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

 /*   public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }*/

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MaterialRequest> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialRequest> materialList) {
        this.materialList = materialList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMaterialRequest that = (UserMaterialRequest) o;
        return id == that.id &&
                userId == that.userId &&
//                materialId == that.materialId &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, userId);
    }
}
