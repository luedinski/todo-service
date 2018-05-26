package com.encoway.todo.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TASK")
public class Task {

    @Id
    @Column(name = "TASK_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "DESCR", length = 50)
    private String name;
    @Column(name = "DONE_STAT", nullable = false)
    private boolean done;

    public Task(String name) {
        this.name = name;
    }

    protected Task() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public Long getId() {
        return id;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("Task[id=%d, name='%s']", id, name);
    }

}
