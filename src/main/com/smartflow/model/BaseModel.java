package main.com.smartflow.model;

import main.com.smartflow.util.ModelToJsonUtil;

public class BaseModel {
    // Used for logging and debugging purposes, converts the model to a JSON string representation
    
    public String toString() {

        return ModelToJsonUtil.convertToJson(this);
    }

}
