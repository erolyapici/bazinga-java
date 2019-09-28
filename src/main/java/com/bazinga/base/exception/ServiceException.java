package com.bazinga.base.exception;

import com.bazinga.base.model.dto.ErrorCode;
import lombok.Data;

import java.util.Arrays;

@Data
public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;

    private Object[] messageArgs;

    private Object[] descriptionArgs;


    public ServiceException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public ServiceException(ErrorCode errorCode, Throwable throwable) {
        this(errorCode, null, null, throwable);
    }

    public ServiceException(ErrorCode errorCode, Object[] messageArgs, Object[] descriptionArgs) {
        this(errorCode, messageArgs, descriptionArgs, null);
    }

    public ServiceException(ErrorCode errorCode, Object[] messageArgs, Object[] descriptionArgs, Throwable throwable) {
        super(errorCode.getDomain(), throwable);
        this.errorCode = errorCode;
        this.messageArgs = messageArgs != null ? Arrays.copyOf(messageArgs, messageArgs.length) : null;
        this.descriptionArgs = descriptionArgs != null ? Arrays.copyOf(descriptionArgs, descriptionArgs.length) : null;
    }

}
