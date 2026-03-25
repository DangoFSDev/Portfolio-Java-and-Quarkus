package com.smartflow.interactor;

import com.smartflow.model.dto.User;
import com.smartflow.response.UserResponse;

public interface ValidateUserCredentials {

    UserResponse execute(User dto);

}
