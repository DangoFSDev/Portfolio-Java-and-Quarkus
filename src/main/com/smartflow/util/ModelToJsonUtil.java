package main.com.smartflow.util;

import java.util.Map;

import jakarta.ws.rs.BadRequestException;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class ModelToJsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final LoggerUtil loggerUtil = new LoggerUtil();

    private ModelToJsonUtil() {
        // Private constructor to prevent instantiation
    }

    public static <T> T convertToDTO(String json, Class<T> clazz) {

        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            loggerUtil.errorLog("Error converting object to JSON: " + e.getMessage(), e);
            try {
                Map<String, Object> map = mapper.readValue(json, Map.class);
                return mapper.convertValue(map, clazz);
            } catch (Exception ex) {
                loggerUtil.errorLog("Error converting error message to JSON: " + ex.getMessage(), ex);
                throw new BadRequestException("Invalid JSON format: " + ex.getMessage(), ex);
            }
        }
    }

    public static String convertToJson(Object obj) {

        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            loggerUtil.errorLog("Error converting object to JSON: " + e.getMessage(), e);
            return "";
        }
    }

}
