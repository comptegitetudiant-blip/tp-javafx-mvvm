package com.example.exercice.repo;

import com.example.exercice.model.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class TodoRepository {
    private final ObservableList<Todo> todos = FXCollections.observableArrayList();

    public ObservableList<Todo> findAll() {
        return FXCollections.unmodifiableObservableList(todos);
    }

    public void add(String title) {
        add(new Todo(String.valueOf(todos.size() + 1), title, false, LocalDate.now().plusDays(1)));
    }

    public void add(Todo todo) {
        todos.add(todo);
    }

    public void remove(Todo todo) {
        todos.remove(todo);
    }

    public void clear() {
        todos.clear();
    }
}
