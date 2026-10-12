package com.dev.cleiton.todo_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.dev.cleiton.todo_api.Models.Task;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    String password_hash,
    LocalDateTime createdAt,
    List<Task> tasks
) {

}
