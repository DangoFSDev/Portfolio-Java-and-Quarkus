package main.com.smartflow.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {

        super(message);
    }

}
