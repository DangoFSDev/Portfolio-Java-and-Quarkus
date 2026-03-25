package com.smartflow.interactor.impl;

import com.smartflow.gateway.UserGateway;
import com.smartflow.interactor.ValidateUserCredentials;
import com.smartflow.mapper.UserMapper;
import com.smartflow.model.dto.User;
import com.smartflow.response.UserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateUserCredentialsImpl implements ValidateUserCredentials {

    private final UserGateway userGateway;
    private final UserMapper userMapper;

    @Override
    public UserResponse execute(User dto) {

        return userMapper.toResponse(userGateway.validateUserCredentials(dto));
    }

}
