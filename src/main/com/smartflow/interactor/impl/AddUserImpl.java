package main.com.smartflow.interactor.impl;

import main.com.smartflow.exception.AlreadyExistsException;
import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.interactor.AddUser;
import main.com.smartflow.interactor.FindExistingUser;
import main.com.smartflow.model.dto.User;

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
