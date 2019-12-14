package com.bazinga.auth.model.util;

import com.bazinga.base.configuration.TokenConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    private TokenConfiguration tokenConfiguration;

    @BeforeEach
    void setUp() {
        tokenConfiguration = new TokenConfiguration();
        tokenConfiguration.setSecret("tasdfest");
        tokenConfiguration.setExpireTime(120000);
        jwtUtil = new JwtUtil(tokenConfiguration);
    }

    @Test
    void shouldReturnToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        String token = jwtUtil.build("test", map);
        Assertions.assertNotNull(token);


    }
}
