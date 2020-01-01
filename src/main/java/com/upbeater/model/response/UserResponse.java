package com.upbeater.model.response;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Integer status;
    private List<UserTypeResponse> userTypes = new ArrayList<>(0);

    public UserResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserResponse(int id, String firstName, String lastName, String email, String userName, Integer status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.status = status;
    }

    public List<UserTypeResponse> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserTypeResponse> userTypes) {
        this.userTypes = userTypes;
    }
}
