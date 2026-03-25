package com.smartflow.gateway;

import com.smartflow.model.dto.User;

public interface UserGateway {

    User validateUserCredentials(User dto);

    User saveUser(User dto);

    String deleteUser(Long id);

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByDTO(User dto);

}
