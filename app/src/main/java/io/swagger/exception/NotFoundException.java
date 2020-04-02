package io.swagger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]"
)

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class NotFoundException extends Exception {

    public NotFoundException(final String msg) {
        super();
    }
}
