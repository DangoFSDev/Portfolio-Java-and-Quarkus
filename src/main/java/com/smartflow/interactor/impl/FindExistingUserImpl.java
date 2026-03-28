package com.smartflow.interactor.impl;

import com.smartflow.gateway.UserGateway;
import com.smartflow.interactor.FindExistingUser;
import com.smartflow.model.dto.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindExistingUserImpl implements FindExistingUser {

    private final UserGateway userGateway;

    @Override
    public User execute(User dto) {

        return userGateway.findUserByDTO(dto);
    }

    @Override
    public User execute(String username) {

        return userGateway.findUserByUsername(username);
    }

    @Override
    public User execute(Long id) {

        return userGateway.findUserById(id);
    }

}
