package main.com.smartflow.interactor.impl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import main.com.smartflow.domain.model.dto.User;
import main.com.smartflow.infrastructure.client.UserGateway;
import main.com.smartflow.interactor.GetUsers;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class GetUsersImpl implements GetUsers {

    private final UserGateway userGateway;

    @Override
    public List<User> execute() {

        return userGateway.getUsers();
    }

}
