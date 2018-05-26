package com.encoway.rest.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TasksServiceExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ TaskListNotFoundException.class, TaskNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorWrapper handleNotFound(Exception e) {
        return new ErrorWrapper(e.getMessage());
    }

    public static class ErrorWrapper {

        public ErrorWrapper(String message) {
            super();
            this.message = message;
        }

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }

    }
}
