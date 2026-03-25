package com.smartflow.interactor.impl;

import com.smartflow.exception.AlreadyExistsException;
import com.smartflow.gateway.UserGateway;
import com.smartflow.interactor.AddUser;
import com.smartflow.interactor.FindExistingUser;
import com.smartflow.model.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddUserImpl implements AddUser {

    private final FindExistingUser findExistingUser;
    private final UserGateway userGateway;

    @Override
    public User execute(User dto) {

        User existingUser = findExistingUser.execute(dto);

        if (existingUser != null) {
            throw new AlreadyExistsException("User already exists");
        }

        return userGateway.saveUser(dto);
    }

}
