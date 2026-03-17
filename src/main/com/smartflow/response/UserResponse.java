package main.com.smartflow.response;

import java.time.Instant;

import main.com.smartflow.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
