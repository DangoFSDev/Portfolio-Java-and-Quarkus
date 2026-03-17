package main.com.smartflow.response;

import main.com.smartflow.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse extends BaseModel {

    private String status;
    private String message;
    private User user;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    class User {

        private Long userId;
        private String userName;
        private String userPass;

    }

}
