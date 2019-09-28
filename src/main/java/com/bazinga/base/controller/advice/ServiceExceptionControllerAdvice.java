package com.bazinga.base.controller.advice;

import com.bazinga.base.component.MessageResourceReader;
import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.base.model.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private MessageResourceReader messageResourceReader;

    public ServiceExceptionControllerAdvice(MessageResourceReader messageResourceReader) {
        this.messageResourceReader = messageResourceReader;
    }

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity handleConflict(Exception ex, WebRequest request) {
        EndpointResponse endpointResponse = new EndpointResponse();
        endpointResponse.setErrorMessage(buildMessage(ex));
        endpointResponse.setStatus(HttpStatus.NOT_FOUND);
        return handleExceptionInternal(ex, endpointResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ErrorMessage buildMessage(Exception ex) {
        if (ex instanceof ServiceException) {
            ErrorCode errorCode = ((ServiceException) ex).getErrorCode();
            Object[] messageArgs = ((ServiceException) ex).getMessageArgs();
            String code = messageResourceReader.getCode(errorCode.getDomain());
            String message = messageResourceReader.getMessageWithArgs(errorCode.getDomain(), messageArgs);
            return new ErrorMessage(code, message);
        }
        return null;
    }
}
