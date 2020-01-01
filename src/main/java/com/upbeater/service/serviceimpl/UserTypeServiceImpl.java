package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.UserType;
import com.upbeater.model.response.UserTypeResponse;
import com.upbeater.repository.UserTypeRepository;
import com.upbeater.service.UserTypeService;
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
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createUserType(UserType userType) {
        ResponseObject responseObject;
        if (userType != null) {
            userType.setCreatedDate(new Date());
            UserType savedUserType = this.userTypeRepository.save(userType);
            responseObject = new ResponseObject("User Type Successfully saved!", true, savedUserType);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("User Type Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserTypeResponse> getAllUserTypes() {
        List<UserType> allActiveUserTypeList = this.userTypeRepository.findAllByStatusNot(StatusTypes.DEACTIVATE.getStatusId());
        List<UserTypeResponse> userTypeResponseList = new ArrayList<>();
        if (allActiveUserTypeList != null) {
            for (UserType userType : allActiveUserTypeList) {
                UserTypeResponse userTypeResponse = new UserTypeResponse();
                userTypeResponse.setId(userType.getId());
                userTypeResponse.setRole(userType.getName());
                userTypeResponseList.add(userTypeResponse);
            }
            return userTypeResponseList;
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateUserType(UserType userType) {
        ResponseObject responseObject;
        if (userType != null) {
            UserType findUserType = this.userTypeRepository.findUserTypesById(userType.getId());
            findUserType.setName(userType.getName());
            findUserType.setStatus(userType.getStatus());
            findUserType.setCreatedDate(new Date());
            UserType updatedUserType = this.userTypeRepository.save(findUserType);
            responseObject = new ResponseObject("User Type Successfully Updated!", true, updatedUserType);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("User Type Updating Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteUserType(int id) {
        ResponseObject responseObject;
        if (id != 0) {
            UserType findUserType = this.userTypeRepository.findUserTypesById(id);
            findUserType.setStatus(StatusTypes.DEACTIVATE.getStatusId());
            findUserType.setCreatedDate(new Date());
            UserType deletedUserType = this.userTypeRepository.save(findUserType);
            responseObject = new ResponseObject("User Type Successfully Deleted!", true, deletedUserType);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("User Type Deleting Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
