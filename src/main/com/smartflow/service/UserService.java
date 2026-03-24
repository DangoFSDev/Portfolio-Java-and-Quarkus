package main.com.smartflow.service;

import main.com.smartflow.interactor.AddUser;
import main.com.smartflow.interactor.DeleteUser;
import main.com.smartflow.interactor.UpdateUser;
import main.com.smartflow.interactor.ValidateUserCredentials;
import main.com.smartflow.mapper.UserMapper;
import main.com.smartflow.model.dto.User;
import main.com.smartflow.request.UserRequest;
import main.com.smartflow.response.UserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final AddUser addUser;
    private final DeleteUser deleteUser;
    private final UserMapper userMapper;
    private final UpdateUser updateUser;
    private final ValidateUserCredentials validateUserCredentials;

    public UserResponse userLogin(UserRequest request) {

        User dto = userMapper.toDTO(request);

        return validateUserCredentials.execute(dto);
    }

    public UserResponse addUser(UserRequest request) {

        User dto = userMapper.toDTO(request);
        return userMapper.toUserResponse(addUser.execute(dto));
    }

    public UserResponse updateUser(UserRequest request) {

        User dto = userMapper.toDTO(request);
        return userMapper.toUserResponse(updateUser.execute(dto));
    }

    public UserResponse deleteUser(Long id) {

        return userMapper.toUserResponse(deleteUser.execute(id));
    }

}
