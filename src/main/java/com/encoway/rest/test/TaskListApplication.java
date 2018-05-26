package com.encoway.rest.test;

import com.encoway.todo.persistence.dao.Task;
import com.encoway.todo.persistence.dao.TodoList;
import com.encoway.todo.persistence.repository.TodoListRepository;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:/META-INF/application.properties")
@EntityScan("com.encoway.todo.persistence.dao")
@EnableJpaRepositories("com.encoway.todo.persistence.repository")
public class TaskListApplication {

    private static final Logger log = LoggerFactory.getLogger(TaskListApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskListApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TodoListRepository repository) {
        return args -> {
            // save a couple of users
            TodoList todoList = new TodoList("Horst");
            todoList.setTasks(Arrays.asList(new Task("Einkaufen"), new Task("Tanken"), new Task("Geschenk besorgen")));
            repository.save(todoList);
            repository.save(new TodoList("Peter"));
            repository.save(new TodoList("Susanne"));
            repository.save(new TodoList("Lüder"));
            repository.save(new TodoList("Lars"));
            // fetch all users
            log.info("User found with findAll():");
            log.info("-------------------------------");
            StreamSupport.stream(repository.findAll().spliterator(), false).map(TodoList::toString).forEach(log::info);
            log.info("");

            // fetch an individual users by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("User found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch users by last name
            log.info("Customer found with findByName('Peter'):");
            log.info("--------------------------------------------");
            repository.findByName("Peter").stream().map(TodoList::toString).forEach(log::info);
            log.info("");
        };
    }
}
