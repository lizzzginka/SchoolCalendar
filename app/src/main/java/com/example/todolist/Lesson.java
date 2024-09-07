package com.example.todolist;

import java.util.List;

public class Lesson {
    private String name;
    private List<Task> tasks;
    private boolean isExpanded;

    public Lesson(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
        this.isExpanded = false;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
