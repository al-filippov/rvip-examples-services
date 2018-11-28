package ru.ulstu.is.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response<T> extends ResponseEntity<Object> {
    public Response(T response) {
        super(new ControllerResponse<>(response, ""), HttpStatus.OK);
    }

    public Response(T response, String error) {
        super(new ControllerResponse<>(response, error), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
