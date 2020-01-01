package com.upbeater.model.auxiliary;

import java.io.Serializable;

public class MaterialAuxiliary implements Serializable {
    private int materialId;
    private String materialTopic;
    private String materialDescription;

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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
}
