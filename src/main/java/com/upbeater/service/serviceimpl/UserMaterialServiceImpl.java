package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.Material;
import com.upbeater.model.dom.User;
import com.upbeater.model.dom.UserMaterial;
import com.upbeater.model.auxiliary.MaterialAuxiliary;
import com.upbeater.model.auxiliary.UserAuxiliary;
import com.upbeater.model.request.MaterialRequest;
import com.upbeater.model.request.UserMaterialRequest;
import com.upbeater.model.response.UserMaterialResponse;
import com.upbeater.repository.MaterialRepository;
import com.upbeater.repository.UserMaterialRepository;
import com.upbeater.repository.UserRepository;
import com.upbeater.service.UserMaterialService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserMaterialServiceImpl implements UserMaterialService {

    private final UserMaterialRepository userMaterialRepository;
    private final MaterialRepository materialRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserMaterialServiceImpl(UserMaterialRepository userMaterialRepository,
                                   MaterialRepository materialRepository,
                                   UserRepository userRepository) {
        this.userMaterialRepository = userMaterialRepository;
        this.materialRepository = materialRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserMaterial> getAllUserMaterials(int id) {
        return this.userMaterialRepository.findAllByUserIdAndStatusNot(id, StatusTypes.DEACTIVATE.getStatusId());
    }

    @Override
    public ResponseEntity<ResponseObject> createUserMaterial(UserMaterialRequest userMaterialRequest) {
        ResponseObject responseObject;
        List<UserMaterial> userMaterialList = new ArrayList<>();
        if (userMaterialRequest != null) {
            for (MaterialRequest mr : userMaterialRequest.getMaterialList()) {
                UserMaterial userMaterial = new UserMaterial();
                Material findMaterial = materialRepository.findMaterialByIdAndStatusNot(mr.getId(), StatusTypes.DEACTIVATE.getStatusId());
                if (findMaterial != null && findMaterial.getId() > 0) {
                    userMaterial.setStatus(userMaterialRequest.getStatus());
                    userMaterial.setUserId(userMaterialRequest.getUserId());
                    userMaterial.setMaterialId(mr.getId());
                    userMaterial.setCreatedDate(new Date());
                    userMaterialList.add(userMaterial);
                } else {
                    responseObject = new ResponseObject("Cannot find Material!", false, null);
                    return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
                }
            }
            if (userMaterialList != null) {
                List<UserMaterial> savedUserMaterials = userMaterialRepository.saveAll(userMaterialList);
                responseObject = new ResponseObject("User Material Saved Successfully!", true, savedUserMaterials);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            } else {
                responseObject = new ResponseObject("User Material Saving Error!", false, null);
                return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseObject = new ResponseObject("User Material Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserMaterialResponse getUserMaterialsByUserId(int userId) {
        if (userId > 0) {
            UserMaterialResponse userMaterialResponse = new UserMaterialResponse();
            UserAuxiliary userAuxiliary = new UserAuxiliary();
            List<MaterialAuxiliary> materialAuxiliaryList = new ArrayList<>();
            User findUser = this.userRepository.findUsersByIdAndStatusNot(userId, StatusTypes.DEACTIVATE.getStatusId());
            if (findUser != null) {
                List<UserMaterial> findUserMaterialList = this.userMaterialRepository.findAllByUserIdAndStatusNot(findUser.getId(), StatusTypes.DEACTIVATE.getStatusId());
                userAuxiliary.setUserId(findUser.getId());
                userAuxiliary.setFirstName(findUser.getFirstName());
                userAuxiliary.setLastName(findUser.getLastName());
                userAuxiliary.setUserName(findUser.getUserName());
                userAuxiliary.setEmail(findUser.getEmail());

                userMaterialResponse.setUser(userAuxiliary);

                if (findUserMaterialList != null) {
                    for (UserMaterial userMaterial : findUserMaterialList) {
                        MaterialAuxiliary materialAuxiliary = new MaterialAuxiliary();
                        Material findMaterial = this.materialRepository.findMaterialByIdAndStatusNot(userMaterial.getMaterialId(), StatusTypes.DEACTIVATE.getStatusId());
                        materialAuxiliary.setMaterialId(findMaterial.getId());
                        materialAuxiliary.setMaterialTopic(findMaterial.getMaterialTopic());
                        materialAuxiliary.setMaterialDescription(findMaterial.getMaterialDescription());
                        materialAuxiliaryList.add(materialAuxiliary);
                    }
                    userMaterialResponse.setMaterialList(materialAuxiliaryList);
                } else {
                    return null;
                }
            }
            return userMaterialResponse;
        } else {
            return null;
        }
    }
}
