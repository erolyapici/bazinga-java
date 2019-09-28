package com.bazinga.auth.model.dto;

import lombok.Data;

@Data
public class UserDetails {

    private final String password;
    private String username;

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
