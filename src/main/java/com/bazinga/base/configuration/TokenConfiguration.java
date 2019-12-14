package com.bazinga.base.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt")
@Configuration
@Data
public class TokenConfiguration {

    private String secret;
    private Integer expireTime;

}
