package main.com.smartflow.request;

import java.time.Instant;

import main.com.smartflow.model.BaseModel;

public class UserRequest extends BaseModel {

    public Long userId;
    public String firstName;
    public String lastName;
    public String address;
    public Byte status;
    public String userName;
    public String userPass;
    public Instant createDate;
    public Instant modifyDate;

}
