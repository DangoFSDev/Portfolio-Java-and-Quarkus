package main.com.smartflow.interactor.impl;

import jakarta.ws.rs.NotFoundException;

import main.com.smartflow.exception.AlreadyExistsException;
import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.interactor.FindExistingUser;
import main.com.smartflow.interactor.UpdateUser;
import main.com.smartflow.model.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserImpl implements UpdateUser {

    private final FindExistingUser findExistingUser;
    private final UserGateway userGateway;

    @Override
    public User execute(User dto) {

        User existingUser = findExistingUser.execute(dto);

        if (existingUser == null) {
            throw new NotFoundException("User not found");
        }

        User existingUserWithSameUserName = findExistingUser.execute(dto.getUserName());

        if (existingUserWithSameUserName != null && !existingUserWithSameUserName.getId()
                                                                                 .equals(existingUser.getId())) {
            throw new AlreadyExistsException("Another user with the same username already exists");
        }

        return userGateway.saveUser(dto);
    }

}
