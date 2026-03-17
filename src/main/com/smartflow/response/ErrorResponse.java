package main.com.smartflow.response;

import main.com.smartflow.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseModel {

    private String status;
    private String message;

    public static ErrorResponse initialize(String errorMessage) {

        return new ErrorResponse("error", errorMessage);
    }

}
