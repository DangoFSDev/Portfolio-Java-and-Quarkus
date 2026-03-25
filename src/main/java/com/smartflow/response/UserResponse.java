package com.smartflow.response;

import java.time.Instant;

import com.smartflow.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse extends BaseModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String address;
    private Byte status;
    private String userName;
    private String userPass;
    private Instant createDate;
    private Instant modifyDate;
    private String message;

}
