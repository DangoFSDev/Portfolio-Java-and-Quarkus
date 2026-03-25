package com.smartflow.util;

import java.lang.StackWalker.Option;
import java.time.Instant;

import jakarta.enterprise.context.RequestScoped;

import io.quarkus.logging.Log;

import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static com.smartflow.util.StringUtil.isEmpty;
import static com.smartflow.util.StringUtil.mask16Digits;
import static com.smartflow.util.StringUtil.nullToEmpty;

@RequestScoped
@Setter
public class LoggerUtil {

    private final StackWalker stackWalker = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE);
    private final String SYSTEM = "SYSTEM=%s";
    private final String TYPE = "TYPE=%s_LOG";
    private final String DATE = "DATE=%s";
    private final String USER_ID = "USER_ID=%s";
    private final String API_CODE = "API_CODE=%s";
    private final String HTTP_METHOD = "HTTP_METHOD=%s";
    private final String URL = "URL=%s";
    private final String HTTP_CODE = "HTTP_CODE=%s";
    private final String REQUEST = "REQUEST=%s";
    private final String RESPONSE = "RESPONSE=%s";
    private final String PLATFORM = "PLATFORM=%s";
    private final String METHOD = "METHOD=%s";
    private final String MESSAGE = "MESSAGE=%s";
    private final String DELIMITER = " | ";
    @ConfigProperty(name = "dependency.logging.enabled", defaultValue = "true")
    private boolean enabledLogging;
    @ConfigProperty(name = "dependency.logging.detailed", defaultValue = "true")
    private boolean detailedLogging;
    @ConfigProperty(name = "dependency.logging.response.max-chars", defaultValue = "10000")
    private int responseMaxChars;
    private String userId;
    private String httpMethod;
    private String url;
    private String platform;

    public LoggerUtil() {

        this.userId = "";
        this.httpMethod = "";
        this.url = "";
        this.platform = "";
        this.detailedLogging = true;
        this.responseMaxChars = 10000;
    }

    public void debugLog(String message, String apiCode, Integer httpCode, String request, String response) {

        Log.debug(createLogMessage("DEBUG", message, apiCode, httpCode, request, response));
    }

    public void debugLog(String message) {

        Log.debug(createLogMessage("DEBUG", message, null, null, null, null));
    }

    public void warnLog(String message, Exception exception, String apiCode, Integer httpCode, String request,
            String response) {

        Log.warn(createLogMessage("WARN", message, apiCode, httpCode, request, response), exception);
    }

    public void warnLog(String message, Exception exception) {

        Log.warn(createLogMessage("WARN", message, null, null, null, null), exception);
    }

    public void errorLog(String message, Exception exception, String apiCode, Integer httpCode, String request,
            String response) {

        Log.error(createLogMessage("ERROR", message, apiCode, httpCode, request, response), exception);
    }

    public void errorLog(String message, Exception exception) {

        Log.error(createLogMessage("ERROR", message, null, null, null, null), exception);
    }

    private String createLogMessage(String logLevel, String message, String apiCode, Integer httpCode,
            String request, String response) {

        if (!enabledLogging) {
            return "SmartFlow logging is disabled.";
        }

        String methodName = getClassAndMethod();
        String maskedUrl = mask16Digits(nullToEmpty(url));
        String maskedRequest = mask16Digits(nullToEmpty(request));
        String maskedResponse = mask16Digits(nullToEmpty(response));
        String formattedResponse = formatResponse(maskedResponse);

        String logMessage = String.format(messageTemplate(), "SmartFlow MS", logLevel, Instant.now(),
                                          nullToEmpty(userId), nullToEmpty(apiCode), nullToEmpty(httpMethod), maskedUrl,
                                          httpCode, maskedRequest, formattedResponse,
                                          nullToEmpty(platform), methodName, message);

        if (isEmpty(userId)) {
            logMessage = logMessage.replace(String.format(DELIMITER + USER_ID, nullToEmpty(userId)), "");
        }
        if (isEmpty(httpMethod)) {
            logMessage = logMessage.replace(String.format(DELIMITER + HTTP_METHOD, nullToEmpty(httpMethod)), "");
        }
        if (isEmpty(maskedUrl)) {
            logMessage = logMessage.replace(String.format(DELIMITER + URL, nullToEmpty(maskedUrl)), "");
        }
        if (isEmpty(apiCode)) {
            logMessage = logMessage.replace(String.format(DELIMITER + API_CODE, nullToEmpty(apiCode)), "");
        }
        if (!isEmpty(String.valueOf(httpCode))) {
            logMessage = logMessage.replace(String.format(DELIMITER + HTTP_CODE, nullToEmpty(String.valueOf(httpCode))),
                                            "");
        }
        if (isEmpty(maskedRequest)) {
            logMessage = logMessage.replace(String.format(DELIMITER + REQUEST, nullToEmpty(maskedRequest)), "");
        }
        if (isEmpty(formattedResponse)) {
            logMessage = logMessage.replace(String.format(DELIMITER + REQUEST, nullToEmpty(formattedResponse)), "");
        }
        if (isEmpty(platform)) {
            logMessage = logMessage.replace(String.format(DELIMITER + REQUEST, nullToEmpty(platform)), "");
        }

        return logMessage;
    }

    private String messageTemplate() {

        return String.join(DELIMITER, SYSTEM, TYPE, DATE, USER_ID, API_CODE, HTTP_METHOD, URL, HTTP_CODE, REQUEST,
                           RESPONSE, PLATFORM, METHOD, MESSAGE);
    }

    private String getClassAndMethod() {

        return stackWalker.walk(frames -> frames.skip(2).findFirst())
                          .map(frame -> String.join("#", frame.getDeclaringClass().getSimpleName(),
                                                    frame.getMethodName()))
                          .orElse("UnknownClass#UnknownMethod");
    }

    private String formatResponse(String response) {

        if (!detailedLogging) {
            return "Response logging is disabled.";
        }
        if (isEmpty(response)) {
            return "";
        }

        int limit = Math.max(0, responseMaxChars);
        if (limit == 0) {
            return "";
        }
        if (response.length() <= limit) {
            return response;
        }

        String ellipsis = "...";
        int slice = Math.max(0, limit - ellipsis.length());
        return response.substring(0, slice) + ellipsis;
    }

}
