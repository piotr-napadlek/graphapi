package io.napadlek.graphapi.rest;

import java.util.List;

public class ErrorResponse {
    private boolean businessException;
    private String message;
    private String exceptionClass;
    private List<String> stackTrace;

    public boolean isBusinessException() {
        return businessException;
    }

    public void setBusinessException(boolean businessException) {
        this.businessException = businessException;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }
}
