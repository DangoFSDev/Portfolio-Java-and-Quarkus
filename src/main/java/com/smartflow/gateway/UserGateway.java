package com.smartflow.gateway;

import java.util.List;

import com.smartflow.model.dto.User;

public interface UserGateway {

    User validateUserCredentials(User dto);

    User saveUser(User dto);

    String deleteUser(Long id);

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByDTO(User dto);

    List<User> getAllUsers();

}
