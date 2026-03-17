package main.com.smartflow.interactor.impl;

import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.interactor.ValidateUserCredentials;
import main.com.smartflow.mapper.UserMapper;
import main.com.smartflow.model.dto.LoginUser;
import main.com.smartflow.response.LoginResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateUserCredentialsImpl implements ValidateUserCredentials {

    private final UserGateway userGateway;
    private final UserMapper userMapper;

    @Override
    public LoginResponse execute(LoginUser dto) {

        return userMapper.toResponse(userGateway.validateUserCredentials(dto));
    }

}
