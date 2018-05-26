package com.encoway.rest.test;

import com.encoway.todo.persistence.dao.Task;
import com.encoway.todo.persistence.dao.TodoList;
import com.encoway.todo.persistence.repository.TaskRepository;
import com.encoway.todo.persistence.repository.TodoListRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class TasksController {

    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{listId}")
    public List<Task> tasks(@PathVariable String listId) {
        return getTasks(listId);
    }

    private List<Task> getTasks(String listId) {
        return todoListRepository.findByName(listId).stream().findFirst().map(TodoList::getTasks).orElseThrow(() -> new TaskListNotFoundException(listId));
    }

    private TodoList getTodoList(String id) {
        return todoListRepository.findByName(id).stream().findFirst().orElseThrow(() -> new TaskListNotFoundException(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{listId}")
    public Task addTask(@PathVariable String listId, @RequestBody String newTask) {
        Task task = taskRepository.save(new Task(newTask));
        TodoList todoList = getTodoList(listId);
        todoList.getTasks().add(task);
        todoListRepository.save(todoList);
        return task;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/task/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        // TodoList todoList = getTodoList(listId);
        // List<Task> tasks = getTask(taskId);
        // tasks.stream().filter(t -> t.getId().equals(taskId)).findFirst().ifPresent(tasks::remove);
        // todoListRepository.save(todoList);
        taskRepository.deleteById(taskId);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{taskId}/done")
    public void setDoneTask(@PathVariable Long taskId) {
        setDone(taskId, true);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{taskId}/undone")
    public void setUndoneTask(@PathVariable Long taskId) {
        setDone(taskId, false);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{taskId}/rename")
    public void setUndoneTask(@PathVariable Long taskId, @RequestBody String newName) {
        Task task = getTask(taskId);
        task.setName(newName);
        taskRepository.save(task);
    }

    private void setDone(Long taskId, boolean doneStatus) {
        Task task = getTask(taskId);
        task.setDone(doneStatus);
        taskRepository.save(task);
    }

    private Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(String.valueOf(taskId)));
    }

}
