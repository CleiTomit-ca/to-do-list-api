package com.dev.cleiton.todo_api.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.cleiton.todo_api.Models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

    Optional<Task> findByTitle(String title);
}
