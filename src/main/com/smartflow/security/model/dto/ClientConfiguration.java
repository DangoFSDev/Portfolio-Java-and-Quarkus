package main.com.smartflow.security.model.dto;

import java.util.Map;

import io.quarkus.runtime.annotations.StaticInitSafe;

import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "security.client")
public interface ClientConfiguration {

    Map<String, Credential> platforms();

    interface Credential {

        String clientId();

        String clientSecret();

    }

}
