package com.smartflow.interactor.impl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import com.smartflow.gateway.UserGateway;
import com.smartflow.interactor.GetUsers;
import com.smartflow.model.dto.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class GetUsersImpl implements GetUsers {

    private final UserGateway userGateway;

    @Override
    public List<User> execute() {

        return userGateway.getAllUsers();
    }

}
