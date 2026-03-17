package main.com.smartflow.security.model.dto;

import jakarta.ws.rs.container.ContainerRequestContext;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static main.com.smartflow.security.constants.HeaderConstants.CLIENT_ID;
import static main.com.smartflow.security.constants.HeaderConstants.CLIENT_SECRET;
import static main.com.smartflow.security.constants.HeaderConstants.DYNATRACE_ID;
import static main.com.smartflow.security.constants.HeaderConstants.PLATFORM;
import static main.com.smartflow.security.constants.HeaderConstants.VERSION;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Headers {

    private String clientId;
    private String clientSecret;
    private String dynatraceId;
    private String platformKey;
    private String platform;
    private String version;

    public Headers(ContainerRequestContext ctx) {

        this.clientId = ctx.getHeaderString(CLIENT_ID);
        this.clientSecret = ctx.getHeaderString(CLIENT_SECRET);
        this.dynatraceId = ctx.getHeaderString(DYNATRACE_ID);
        this.platform = ctx.getHeaderString(PLATFORM);
        this.version = ctx.getHeaderString(VERSION);
    }

}
