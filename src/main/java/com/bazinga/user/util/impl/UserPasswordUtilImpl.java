package com.bazinga.user.util.impl;

import com.bazinga.user.configuration.PasswordConfiguration;
import com.bazinga.user.util.UserPasswordUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPasswordUtilImpl implements UserPasswordUtil {
    private PasswordEncoder passwordEncoder;
    private PasswordConfiguration passwordConfiguration;

    public UserPasswordUtilImpl(PasswordEncoder passwordEncoder, PasswordConfiguration passwordConfiguration) {
        this.passwordEncoder = passwordEncoder;
        this.passwordConfiguration = passwordConfiguration;
    }

    @Override
    public Optional<String> encode(String password) {
        String s = passwordConfiguration.getSecret() + password + passwordConfiguration.getSecret();
        return Optional.of(passwordEncoder.encode(s));
    }

    @Override
    public boolean match(String password, String encodedPassword) {
        String s = passwordConfiguration.getSecret() + password + passwordConfiguration.getSecret();
        return passwordEncoder.matches(s, encodedPassword);
    }

}
