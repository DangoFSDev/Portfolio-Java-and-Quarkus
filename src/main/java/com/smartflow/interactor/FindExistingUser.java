package com.smartflow.interactor;

import com.smartflow.model.dto.User;

public interface FindExistingUser {

    User execute(User dto);

    User execute(String username);

    User execute(Long id);

}
