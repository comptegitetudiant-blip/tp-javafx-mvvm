package com.example.exercice.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Todo {
    private final String id;
    private final StringProperty title = new SimpleStringProperty();
    private final BooleanProperty done = new SimpleBooleanProperty(false);
    private final ObjectProperty<LocalDate> deadline = new SimpleObjectProperty<>();

    public Todo(String id, String title, boolean done, LocalDate deadline) {
        this.id = id;
        this.title.set(title);
        this.done.set(done);
        this.deadline.set(deadline);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String v) {
        title.set(v);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public boolean isDone() {
        return done.get();
    }

    public void setDone(boolean v) {
        done.set(v);
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public LocalDate getDeadline() {
        return deadline.get();
    }

    public void setDeadline(LocalDate v) {
        deadline.set(v);
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }

    public int getTitleLength() {
        return getTitle().length();
    }
}
