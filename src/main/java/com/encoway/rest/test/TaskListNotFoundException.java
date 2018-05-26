package com.encoway.rest.test;

public class TaskListNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5728375990464397836L;

    public TaskListNotFoundException(String taskListId) {
        super("could not find user '" + taskListId + "'.");
    }
}