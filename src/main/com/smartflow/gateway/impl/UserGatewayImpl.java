package main.com.smartflow.gateway.impl;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.mapper.UserMapper;
import main.com.smartflow.model.dto.LoginUser;
import main.com.smartflow.model.dto.User;
import main.com.smartflow.model.entity.UserEntity;
import main.com.smartflow.repository.UserRepository;
import main.com.smartflow.util.LoggerUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final LoggerUtil loggerUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User validateUserCredentials(LoginUser dto) {

        try {
            loggerUtil.debugLog("Validating user credentials for user: " + dto.getUserName());

            UserEntity validateUser = userMapper.toEntity(dto);
            UserEntity validated = userRepository.findUserNameAndUserPass(validateUser);

            return userMapper.toDTO(validated);
        } catch (Exception e) {
            loggerUtil.errorLog("Error validating user credentials: " + e.getMessage(), e, null, null, dto.toString(),
                                null);
            throw new NotFoundException(e);
        }
    }

    @Override
    public User saveUser(User dto) {

        try {
            loggerUtil.debugLog("Finding existing user: " + dto.getUserName());

            UserEntity oldEntity = userRepository.findUserName(dto.getUserName());

            UserEntity entityToUpsert;
            if (dto.getId() != null) {
                entityToUpsert = userMapper.updateEntity(oldEntity, dto);
            } else {
                entityToUpsert = userMapper.toEntity(dto);
            }

            userRepository.save(entityToUpsert);

            return userMapper.toDTO(entityToUpsert);
        } catch (Exception e) {
            loggerUtil.errorLog("Error finding existing user: " + e.getMessage(), e, null, null, dto.toString(),
                                null);
            throw new BadRequestException(e);
        }
    }

}
