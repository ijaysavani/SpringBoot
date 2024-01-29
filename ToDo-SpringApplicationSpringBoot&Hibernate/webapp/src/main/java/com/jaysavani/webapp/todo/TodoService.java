package com.jaysavani.webapp.todo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;


    static {
        todos.add(new Todo(++todosCount, "jay", "Azure", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "jay", "AWS", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "jay", "GCP", LocalDate.now().plusYears(3), false));
    }


    public List<Todo> findByUsername(String username) {

        Predicate<? super Todo> predicate= todo ->  todo.getUsername().equals(username);
        return todos.stream().filter(predicate).toList() ;
    }

    public void add(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(++todosCount, username, description, targetDate, done));
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate= todo ->  todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {

        Predicate<? super Todo> predicate= todo ->  todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);

    }


    private String getLoggedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
