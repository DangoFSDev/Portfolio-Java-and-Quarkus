package com.smartflow.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.smartflow.model.BaseModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRequest extends BaseModel {

    public Long userId;
    public String firstName;
    public String lastName;
    public String address;
    public Byte status;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    public String userName;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    public String userPass;

}