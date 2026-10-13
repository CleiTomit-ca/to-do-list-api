package com.dev.cleiton.todo_api.Exceptions;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String messagem){
        super(messagem);
    }
}
