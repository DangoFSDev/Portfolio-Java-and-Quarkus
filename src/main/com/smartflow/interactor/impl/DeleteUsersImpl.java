package main.com.smartflow.interactor.impl;

import jakarta.enterprise.context.ApplicationScoped;

import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.interactor.DeleteUser;
import main.com.smartflow.interactor.FindExistingUser;
import main.com.smartflow.model.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class DeleteUsersImpl implements DeleteUser {

    private final UserGateway userGateway;
    private final FindExistingUser findExistingUser;

    @Override
    public String execute(Long id) {

        User user = findExistingUser.execute(id);

        if (user == null) {
            return "User not found.";
        }

        return userGateway.deleteUser(id);
    }

}
