package main.com.smartflow.interactor;

import main.com.smartflow.model.dto.LoginUser;
import main.com.smartflow.response.LoginResponse;

public interface ValidateUserCredentials {

    LoginResponse execute(LoginUser dto);

}
