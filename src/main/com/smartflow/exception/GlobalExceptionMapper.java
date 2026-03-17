package main.com.smartflow.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import main.com.smartflow.response.ErrorResponse;
import main.com.smartflow.util.LoggerUtil;

import lombok.Builder;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final LoggerUtil logger = new LoggerUtil();

    private static APIExceptionDetails getAPIExceptionDetails(Exception exception) {

        var detailsBuilder = APIExceptionDetails.builder().exception(exception);

        return switch (exception) {
            case NotFoundException ex -> detailsBuilder
                    .status(NOT_FOUND)
                    .errorMessage(ex.getMessage())
                    .build();
            case BadRequestException ex -> detailsBuilder
                    .status(BAD_REQUEST)
                    .errorMessage(ex.getMessage())
                    .build();
            default -> detailsBuilder
                    .status(INTERNAL_SERVER_ERROR)
                    .errorMessage("An unexpected error occurred.")
                    .build();
        };
    }

    @Override
    public Response toResponse(Exception exception) {

        var details = getAPIExceptionDetails(exception);

        logger.errorLog(details.errorMessage(), exception);

        // Return a generic error response
        return Response.status(details.status())
                       .entity(ErrorResponse.initialize(details.errorMessage()))
                       .build();
    }

    @Builder
    record APIExceptionDetails(
            Status status,
            String errorMessage,
            Exception exception
    ) {

    }

}
