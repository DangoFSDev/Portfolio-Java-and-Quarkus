package com.smartflow.model.dto;

import java.time.Instant;

import com.smartflow.model.BaseModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User extends BaseModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Byte status;
    private String userName;
    private String userPass;
    private Instant createDate;
    private Instant modifyDate;

}
