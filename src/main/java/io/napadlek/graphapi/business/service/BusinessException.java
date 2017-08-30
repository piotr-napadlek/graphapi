package io.napadlek.graphapi.business.service;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
