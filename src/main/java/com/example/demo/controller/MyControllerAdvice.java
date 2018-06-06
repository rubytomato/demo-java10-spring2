package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.example.demo.controller")
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> simpleControllerException(HttpServletRequest request, Throwable ex) {
        var status = getStatus(request);
        return new ResponseEntity<>(ex.getMessage(), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        var statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
