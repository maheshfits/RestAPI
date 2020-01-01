package com.upbeater.service.serviceimpl;

import com.upbeater.config.PasswordEncryption;
import com.upbeater.model.dom.UserType;
import com.upbeater.model.auxiliary.ResetPassword;
import com.upbeater.model.dom.User;
import com.upbeater.model.request.UserRequest;
import com.upbeater.model.response.UserResponse;
import com.upbeater.model.response.UserTypeResponse;
import com.upbeater.repository.UserRepository;
import com.upbeater.repository.UserTypeRepository;
import com.upbeater.service.UserService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createUser(UserRequest userRequest) {
        ResponseObject responseObject;
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        User user = new User();
        if (userRequest != null) {
            user.setCreatedDate(new Date());
            user.setPassword(passwordEncryption.SHA256("changeme"));
            user.setImageUrl("default-user.jpg");
            user.setUserName(userRequest.getUserName());
            user.setStatus(userRequest.getStatus());
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            List<UserType> userType = userRequest.getUserType();
            Set<UserType> userTypes = new HashSet<>();
            for (UserType ut : userType) {
                UserType userTypeObject = userTypeRepository.findUserTypesByNameAndStatusNot(ut.getName(), StatusTypes.DEACTIVATE.getStatusId());
                userTypes.add(userTypeObject);
            }
            user.setUserTypes(userTypes);
            User savedUser = this.userRepository.save(user);
            if (savedUser != null) {
                UserResponse userResponse = new UserResponse();
                userResponse.setId(savedUser.getId());
                userResponse.setUserName(savedUser.getUserName());
                userResponse.setFirstName(savedUser.getFirstName());
                userResponse.setLastName(savedUser.getLastName());
                userResponse.setEmail(savedUser.getEmail());
                userResponse.setStatus(savedUser.getStatus());

                List<UserTypeResponse> userTypeResponses = new ArrayList<>();
                for (UserType s : savedUser.getUserTypes()) {
                    UserTypeResponse userTypeResponse = new UserTypeResponse(s.getId(), s.getName());
                    userTypeResponses.add(userTypeResponse);
                }
                userResponse.setUserTypes(userTypeResponses);

                responseObject = new ResponseObject("User Successfully saved!", true, userResponse);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            } else {
                responseObject = new ResponseObject("User Saving Error!", false, null);
                return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
            }

        } else {
            responseObject = new ResponseObject("User Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAllByStatusNot(StatusTypes.DEACTIVATE.getStatusId());
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setEmail(user.getEmail());
            userResponse.setStatus(user.getStatus());
            userResponse.setUserName(user.getUserName());

            List<UserTypeResponse> userTypeResponses = new ArrayList<>();
            for (UserType s : user.getUserTypes()) {
                UserTypeResponse userTypeResponse = new UserTypeResponse(s.getId(), s.getName());
                userTypeResponses.add(userTypeResponse);
            }
            userResponse.setUserTypes(userTypeResponses);

            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public ResponseEntity<ResponseObject> resetUserPassword(ResetPassword resetPassword) {
        ResponseObject responseObject;
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        User findUser = userRepository.findUsersByIdAndStatusNot(resetPassword.getId(), StatusTypes.DEACTIVATE.getStatusId());
        if (findUser.getPassword().equals(passwordEncryption.SHA256(resetPassword.getOldPassword()))) {
            findUser.setPassword(passwordEncryption.SHA256(resetPassword.getNewPassword()));
            findUser.setCreatedDate(new Date());
            User savedUser = this.userRepository.save(findUser);
            responseObject = new ResponseObject("User Password Successfully Updated!", true, null);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Incorrect Current Password!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateUser(User user) {
        ResponseObject responseObject;
        if (user != null && user.getId() > 0) {
            User findUser = this.userRepository.findUserById(user.getId());
            findUser.setFirstName(user.getFirstName());
            findUser.setLastName(user.getLastName());
            findUser.setEmail(user.getEmail());
            findUser.setStatus(user.getStatus());
            findUser.setCreatedDate(new Date());
            User updatedUser = this.userRepository.save(findUser);
            responseObject = new ResponseObject("User Successfully Updated!", true, updatedUser);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("User Updating Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteUser(int id) {
        ResponseObject responseObject;
        if (id != 0) {
            User findUser = this.userRepository.findUsersByIdAndStatusNot(id, StatusTypes.DEACTIVATE.getStatusId());
            if (findUser != null) {
                findUser.setStatus(StatusTypes.DEACTIVATE.getStatusId());
                findUser.setCreatedDate(new Date());
                User deletedUser = this.userRepository.save(findUser);
                responseObject = new ResponseObject("User Successfully Deleted!", true, deletedUser);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            } else {
                responseObject = new ResponseObject("User Not Found!", false, null);
                return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
            }
        } else {
            responseObject = new ResponseObject("User Deleting Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
