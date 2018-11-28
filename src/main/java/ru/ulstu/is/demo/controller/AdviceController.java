package ru.ulstu.is.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.demo.response.Response;

@RestController
@ControllerAdvice
public class AdviceController {
    private final Logger log = LoggerFactory.getLogger(AdviceController.class);

    private Response<Void> handleException(String error) {
        log.warn(error);
        return new Response<>(null, error);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return handleException(e.getMessage());
    }
}
