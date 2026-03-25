package com.smartflow.security.filter;

import java.io.IOException;
import java.util.Map;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

import com.smartflow.response.ErrorResponse;
import com.smartflow.security.model.dto.ClientConfiguration;
import com.smartflow.security.model.dto.Headers;
import com.smartflow.util.LoggerUtil;

import org.apache.http.HttpHeaders;

import static com.smartflow.security.constants.HeaderConstants.DYNATRACE_ID;
import static com.smartflow.security.constants.HeaderConstants.PLATFORM;
import static com.smartflow.util.StringUtil.isEmpty;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ClientAuthenticationFilter implements ContainerRequestFilter {

    private final ClientConfiguration clientConfiguration;
    private final LoggerUtil loggerUtil;
    private Headers header;

    @Inject
    public ClientAuthenticationFilter(ClientConfiguration clientConfiguration, LoggerUtil loggerUtil) {

        this.clientConfiguration = clientConfiguration;
        this.loggerUtil = loggerUtil;
    }

    public ClientAuthenticationFilter() {

        this.clientConfiguration = null;
        this.loggerUtil = new LoggerUtil();
    }

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        header = new Headers(ctx);

        assert clientConfiguration != null;
        Map<String, String> mapKeys = findPlatformKey(clientConfiguration);

        ctx.setProperty(PLATFORM, mapKeys.get(PLATFORM));
        ctx.setProperty(DYNATRACE_ID, mapKeys.get(DYNATRACE_ID));
    }

    private Map<String, String> findPlatformKey(ClientConfiguration clientConfiguration) {

        validateHeaders();

        assert clientConfiguration != null;
        header.setPlatformKey(clientConfiguration.platforms().entrySet().stream()
                                                 .filter(entry -> entry.getValue()
                                                                       .clientId()
                                                                       .equals(header.getClientId())
                                                         && entry.getValue()
                                                                 .clientSecret()
                                                                 .equals(header.getClientSecret()))
                                                 .findFirst()
                                                 .map(Map.Entry::getKey)
                                                 .orElse(null));
        if (isEmpty(header.getPlatformKey())) {
            abortDueToUnauthorizedRequest("Please provide a valid Client Id and Client Secret");
        }

        return Map.of(PLATFORM, header.getPlatformKey(), DYNATRACE_ID, header.getDynatraceId());
    }

    private void validateHeaders() {

        if (isEmpty(header.getClientId()) || isEmpty(header.getClientSecret())) {
            abortDueToUnauthorizedRequest("Missing Client Id or Client Secret in the request headers");
        }

        if (isEmpty(header.getDynatraceId())) {
            abortDueToUnauthorizedRequest("Missing Dynatrace Id in the request headers");
        }

        if (isEmpty(header.getPlatform())) {
            abortDueToBadRequest("Missing Platform in the request headers");
        } else if (header.getPlatform().equals("web") && isEmpty(header.getVersion())) {
            abortDueToBadRequest("Missing Version in the request headers for web platform");
        }

    }

    private void abortDueToUnauthorizedRequest(String errorMessage) {

        WebApplicationException exception = new WebApplicationException(
                Response.status(Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm =\"microservice\"")
                        .entity(ErrorResponse.initialize(errorMessage))
                        .build()
        );
        loggerUtil.warnLog(errorMessage, exception);

        throw exception;
    }

    private void abortDueToBadRequest(String errorMessage) {

        WebApplicationException exception = new WebApplicationException(
                Response.status(Status.BAD_REQUEST)
                        .entity(ErrorResponse.initialize(errorMessage))
                        .build()
        );
        loggerUtil.warnLog(errorMessage, exception);

        throw exception;
    }

}
