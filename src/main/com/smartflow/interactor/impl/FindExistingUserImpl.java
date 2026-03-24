package main.com.smartflow.interactor.impl;

import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.interactor.FindExistingUser;
import main.com.smartflow.model.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindExistingUserImpl implements FindExistingUser {

    private final UserGateway userGateway;

    @Override
    public User execute(User dto) {

        return null;
    }

    @Override
    public User execute(String username) {

        return null;
    }

    @Override
    public User execute(Long id) {

        return null;
    }

}
