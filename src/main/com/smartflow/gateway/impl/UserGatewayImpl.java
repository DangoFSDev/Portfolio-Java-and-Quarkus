package main.com.smartflow.gateway.impl;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import main.com.smartflow.gateway.UserGateway;
import main.com.smartflow.mapper.UserMapper;
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
    public User validateUserCredentials(User dto) {

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

    @Override
    public String deleteUser(Long id) {

        try {
            loggerUtil.debugLog("Deleting user with id: " + id);

            userRepository.deleteById(id);

            return "User with id " + id + " deleted successfully.";
        } catch (Exception e) {
            loggerUtil.errorLog("Error deleting user: " + e.getMessage(), e, null, null, "User ID: " + id, null);
            throw new NotFoundException(e);
        }
    }

    @Override
    public User findUserById(Long id) {

        try {
            loggerUtil.debugLog("Finding user with id: " + id);

            UserEntity entity = userRepository.findById(id);

            return userMapper.toDTO(entity);
        } catch (Exception e) {
            loggerUtil.errorLog("Error finding user: " + e.getMessage(), e, null, null, "User ID: " + id, null);
            throw new NotFoundException(e);
        }
    }

    @Override
    public User findUserByUsername(String username) {

        try {
            loggerUtil.debugLog("Finding user with username: " + username);

            UserEntity entity = userRepository.findUserName(username);

            return userMapper.toDTO(entity);
        } catch (Exception e) {
            loggerUtil.errorLog("Error finding user: " + e.getMessage(), e, null, null, "Username: " + username, null);
            throw new NotFoundException(e);
        }
    }

    @Override
    public User findUserByDTO(User dto) {

        try {
            loggerUtil.debugLog("Finding user with DTO: " + dto.toString());

            UserEntity entity = userMapper.toEntity(dto);
            UserEntity foundEntity = userRepository.findUserNameAndUserPass(entity);

            return userMapper.toDTO(foundEntity);
        } catch (Exception e) {
            loggerUtil.errorLog("Error finding user: " + e.getMessage(), e, null, null, "User DTO: " + dto.toString(),
                                null);
            throw new NotFoundException(e);
        }
    }

}
