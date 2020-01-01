package com.upbeater.service;

import com.upbeater.model.dom.UserMaterial;
import com.upbeater.model.request.UserMaterialRequest;
import com.upbeater.model.response.UserMaterialResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserMaterialService {
    List<UserMaterial> getAllUserMaterials(int id);

    ResponseEntity<ResponseObject> createUserMaterial(UserMaterialRequest userMaterialRequest);

    UserMaterialResponse getUserMaterialsByUserId(int userId);
}
