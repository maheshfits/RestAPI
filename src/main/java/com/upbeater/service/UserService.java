package com.upbeater.service;

import com.upbeater.model.auxiliary.ResetPassword;
import com.upbeater.model.dom.User;
import com.upbeater.model.request.UserRequest;
import com.upbeater.model.response.UserResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<ResponseObject> createUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    ResponseEntity<ResponseObject> resetUserPassword(ResetPassword resetPassword);

    ResponseEntity<ResponseObject> updateUser(User user);

    ResponseEntity<ResponseObject> deleteUser(int id);
}
