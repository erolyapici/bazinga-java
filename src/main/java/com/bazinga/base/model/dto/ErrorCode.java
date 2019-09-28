package com.bazinga.base.model.dto;

import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ErrorCode {

    private String domain;

    public ErrorCode(String domain) {
        this.domain = domain;
    }

    public static ErrorCode build(String... domains) {
        return new ErrorCode(getCompositeDomain(domains));
    }

    private static String getCompositeDomain(String... domains) {
        return (String) Stream.of(domains).collect(Collectors.joining("."));
    }
}
