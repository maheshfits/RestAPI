package com.upbeater.utility;

public enum StatusTypes {
    DEACTIVATE("Deactivate", 0),
    ACTIVE("Activate", 1);

    private String statusType;
    private Integer statusId;

    StatusTypes(String statusType, int statusId) {
        this.statusType = statusType;
        this.statusId = statusId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
