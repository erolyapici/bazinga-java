package com.bazinga.auth.model.request;

import com.bazinga.base.model.request.BaseRequest;
import lombok.Data;

@Data
public class AuthRequest extends BaseRequest {
    private String username;
    private String password;
}
