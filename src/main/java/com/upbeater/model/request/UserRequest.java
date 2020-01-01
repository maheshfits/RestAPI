package com.upbeater.model.request;

import com.upbeater.model.dom.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRequest implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Integer status;
    private List<UserType> userType = new ArrayList<>();

    public UserRequest() {
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

    public UserRequest(int id, String firstName, String lastName, String email, String userName, Integer status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.status = status;
    }

    public List<UserType> getUserType() {
        return userType;
    }

    public void setUserType(List<UserType> userType) {
        this.userType = userType;
    }
}
