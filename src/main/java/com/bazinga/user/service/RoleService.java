package com.bazinga.user.service;

import java.util.Map;
import java.util.Optional;

public interface RoleService {

    Optional<Map<Integer, String>> getRoles();
}
