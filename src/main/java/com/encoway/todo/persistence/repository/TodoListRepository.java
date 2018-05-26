package com.encoway.todo.persistence.repository;

import com.encoway.todo.persistence.dao.TodoList;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {

    List<TodoList> findByName(String name);
}
