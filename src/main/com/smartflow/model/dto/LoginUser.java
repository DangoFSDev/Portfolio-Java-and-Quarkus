package main.com.smartflow.model.dto;

import main.com.smartflow.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginUser extends BaseModel {

    private String userName;
    private String userPass;


}
