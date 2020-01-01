package com.upbeater.service;

import com.upbeater.model.dom.UserType;
import com.upbeater.model.response.UserTypeResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserTypeService {
    ResponseEntity<ResponseObject> createUserType(UserType userType);

    List<UserTypeResponse> getAllUserTypes();

    ResponseEntity<ResponseObject> updateUserType(UserType userType);

    ResponseEntity<ResponseObject> deleteUserType(int id);
}
