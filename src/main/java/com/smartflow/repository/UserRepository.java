package com.smartflow.repository;

import com.smartflow.model.entity.UserEntity;

public class UserRepository implements BaseRepository<UserEntity> {

    public UserEntity findUserNameAndUserPass(UserEntity entity) {

        return (UserEntity) find("userName = ?1 AND userPass = ?2", entity.getUserName(), entity.getUserPass());
    }

    public UserEntity findUserName(String userName) {

        return (UserEntity) find("userName = ?1", userName);
    }

}
