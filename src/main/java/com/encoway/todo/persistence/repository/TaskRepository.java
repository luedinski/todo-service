package com.encoway.todo.persistence.repository;

import com.encoway.todo.persistence.dao.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
