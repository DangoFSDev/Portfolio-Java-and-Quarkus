package main.com.smartflow.exception;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.ValidationException;

import main.com.smartflow.response.ErrorResponse;
import main.com.smartflow.util.LoggerUtil;

@Provider
public class ConstraintValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final LoggerUtil logger = new LoggerUtil();

    @Override
    public Response toResponse(ValidationException exception) {

        logger.warnLog(exception.getMessage(), exception, null, null, null, null);

        String[] violations = exception.getMessage().split(", (?=\\w+\\.\\w+\\[)");

        List<String> errors = new ArrayList<>();

        for (String violation : violations) {
            String[] vio = violation.split(":", 2);
            errors.add(vio[1]);
        }

        String errorMessage = String.join(", ", errors);

        return Response.status(Status.BAD_REQUEST).entity(ErrorResponse.initialize(errorMessage)).build();
    }

}
