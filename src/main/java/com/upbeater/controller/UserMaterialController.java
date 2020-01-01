package com.upbeater.controller;

import com.upbeater.model.dom.UserMaterial;
import com.upbeater.model.request.UserMaterialRequest;
import com.upbeater.model.response.UserMaterialResponse;
import com.upbeater.service.UserMaterialService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/userMaterialController")
public class UserMaterialController {

    private final String APPLICATION_JSON = "application/json";

    private final UserMaterialService userMaterialService;

    @Autowired
    public UserMaterialController(UserMaterialService userMaterialService) {
        this.userMaterialService = userMaterialService;
    }

    @RequestMapping(value = "/getAllUserMaterials/{id}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<UserMaterial> getAllUserMaterials(@PathVariable(value = "id") int id) {
        return userMaterialService.getAllUserMaterials(id);
    }

    @RequestMapping(value = "/createUserMaterial", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createUserMaterial(@RequestBody UserMaterialRequest userMaterialRequest) {
        return userMaterialService.createUserMaterial(userMaterialRequest);
    }

    @RequestMapping(value = "/getUserMaterialsByUserId/{userId}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public UserMaterialResponse getUserMaterialsByUserId(@PathVariable(value = "userId") int userId) {
        return userMaterialService.getUserMaterialsByUserId(userId);
    }
}
