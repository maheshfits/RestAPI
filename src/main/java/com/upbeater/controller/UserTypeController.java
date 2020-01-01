package com.upbeater.controller;

import com.upbeater.model.dom.UserType;
import com.upbeater.model.response.UserTypeResponse;
import com.upbeater.service.UserTypeService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/userTypeController")
public class UserTypeController {
    private final String APPLICATION_JSON = "application/json";

    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @RequestMapping(value = "/createUserType", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createUserType(@RequestBody UserType userType) {
        return userTypeService.createUserType(userType);
    }

    @RequestMapping(value = "/updateUserType", method = RequestMethod.PUT, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> updateUserType(@RequestBody UserType userType) {
        return userTypeService.updateUserType(userType);
    }

    @RequestMapping(value = "/getAllUserTypes", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<UserTypeResponse> getAllUserTypes() {
        return userTypeService.getAllUserTypes();
    }

    @RequestMapping(value = "/deleteUserType/{id}", method = RequestMethod.DELETE, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> deleteUserType(@PathVariable(value = "id") int id) {
        return userTypeService.deleteUserType(id);
    }

}
