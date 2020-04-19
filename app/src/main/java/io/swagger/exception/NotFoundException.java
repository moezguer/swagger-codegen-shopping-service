package io.swagger.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String id) {
        super("ID not found: " + id);
    }
}
