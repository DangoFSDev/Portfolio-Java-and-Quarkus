package main.com.smartflow.gateway;

import main.com.smartflow.model.dto.LoginUser;
import main.com.smartflow.model.dto.User;

public interface UserGateway {

    User validateUserCredentials(LoginUser dto);

    User saveUser(User dto);

}
