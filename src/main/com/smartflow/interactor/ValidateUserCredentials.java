package main.com.smartflow.interactor;

import main.com.smartflow.model.dto.User;
import main.com.smartflow.response.UserResponse;

public interface ValidateUserCredentials {

    UserResponse execute(User dto);

}
