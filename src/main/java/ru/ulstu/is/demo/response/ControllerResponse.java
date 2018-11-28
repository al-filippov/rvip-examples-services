package ru.ulstu.is.demo.response;

import java.util.Optional;

class ControllerResponse<T> {
    private T response;
    private String error;

    ControllerResponse(T response, String error) {
        this.response = response;
        this.error = Optional.ofNullable(error).orElse("");
    }

    public T getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}
