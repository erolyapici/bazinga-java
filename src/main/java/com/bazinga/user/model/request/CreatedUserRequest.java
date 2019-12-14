package com.bazinga.user.model.request;

import com.bazinga.base.model.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CreatedUserRequest extends BaseRequest {
    private static final long serialVersionUID = -8168586110737208099L;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$")
    private String surname;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$")
    private String name;

}
