package com.pawlukowicz.empik.user.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "github.api.config")
@Getter
@Setter
public class GithubApiConfig {
    private @NonNull String baseUrl;
    private @NonNull String userUrl;
}
