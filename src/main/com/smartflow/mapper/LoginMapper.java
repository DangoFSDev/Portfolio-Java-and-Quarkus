package main.com.smartflow.mapper;

import main.com.smartflow.model.dto.LoginUser;
import main.com.smartflow.request.LoginRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface LoginMapper {

    LoginUser toDTO(LoginRequest request);

}
