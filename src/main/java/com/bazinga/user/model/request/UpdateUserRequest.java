package com.bazinga.user.model.request;

import com.bazinga.base.model.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateUserRequest extends BaseRequest {
    private static final long serialVersionUID = -8168586110737208099L;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$")
    private String surname;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$")
    private String name;

}
