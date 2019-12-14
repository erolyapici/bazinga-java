package com.bazinga.user.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties("password")
public class PasswordConfiguration {

    @NotNull
    private String secret;
    @Bean
    public PasswordEncoder encoder() {
        return new Pbkdf2PasswordEncoder(secret);
    }
}
