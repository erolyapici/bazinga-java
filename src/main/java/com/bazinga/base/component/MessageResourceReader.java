package com.bazinga.base.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResourceReader implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(@Qualifier("messageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessageWithArgs(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }

    public String getMessageWithArgs(String key, Object[] args) {
        return getMessageWithArgs(key + ".message", args, null);
    }

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    public String getCode(String key) {
        return getMessage(key + ".code", null);
    }

    public String getMessage(String key, Locale locale) {
        return getMessageWithArgs(key, null, locale);
    }
}
