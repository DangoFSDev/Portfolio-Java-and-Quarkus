package com.smartflow.mapper;

import java.util.List;

import com.smartflow.model.dto.User;
import com.smartflow.model.entity.UserEntity;
import com.smartflow.request.UserRequest;
import com.smartflow.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toEntity(User dto);

    User toDTO(UserEntity entity);

    User toDTO(UserRequest request);

    UserResponse toUserResponse(User dto);

    List<User> toDTOs(List<UserEntity> entities);

    List<UserResponse> toUserResponseList(List<User> dtos);

    default UserResponse toUserResponse(String message) {

        UserResponse response = new UserResponse();
        response.setMessage(message);
        return response;
    }

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    UserEntity updateEntity(@MappingTarget UserEntity entity, User dto);

}
