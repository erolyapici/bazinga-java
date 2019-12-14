package com.bazinga.user.util;

import java.util.Optional;

public interface UserPasswordUtil {
    Optional<String> encode(String password);

    boolean match(String password, String encodedPassword);
}
