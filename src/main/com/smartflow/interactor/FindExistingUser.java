package main.com.smartflow.interactor;

import main.com.smartflow.model.dto.User;

public interface FindExistingUser {

    User execute(User dto);

    User execute(String username);

    User execute(Long id);

}
