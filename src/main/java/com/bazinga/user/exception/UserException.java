package com.bazinga.user.exception;

import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;

public class UserException extends ServiceException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

    public UserException(ErrorCode errorCode, Object[] messageArgs, Object[] descriptionArgs) {
        super(errorCode, messageArgs, descriptionArgs);
    }

    public UserException(ErrorCode errorCode, Object[] messageArgs, Object[] descriptionArgs, Throwable throwable) {
        super(errorCode, messageArgs, descriptionArgs, throwable);
    }
}
