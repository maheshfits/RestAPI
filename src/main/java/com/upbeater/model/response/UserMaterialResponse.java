package com.upbeater.model.response;

import com.upbeater.model.auxiliary.MaterialAuxiliary;
import com.upbeater.model.auxiliary.UserAuxiliary;

import java.util.List;

public class UserMaterialResponse {
    private UserAuxiliary user;
    private List<MaterialAuxiliary> materialList;

    public UserAuxiliary getUser() {
        return user;
    }

    public void setUser(UserAuxiliary user) {
        this.user = user;
    }

    public List<MaterialAuxiliary> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialAuxiliary> materialList) {
        this.materialList = materialList;
    }
}
