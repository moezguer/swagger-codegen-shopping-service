package io.swagger.exception;

public class ApiException extends Exception {
    private int code;

    public ApiException(final int code, final String msg) {
        super(msg);
        this.code = code;
    }
}
