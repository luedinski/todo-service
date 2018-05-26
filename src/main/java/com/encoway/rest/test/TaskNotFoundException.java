package com.encoway.rest.test;

public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3601677624152879675L;

    public TaskNotFoundException(String taskId) {
        super("could not find task '" + taskId + "'.");
    }

}
