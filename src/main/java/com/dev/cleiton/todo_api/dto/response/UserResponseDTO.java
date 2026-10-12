package com.dev.cleiton.todo_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    LocalDateTime createdAt,
    List<TaskResponseDTO> tasks
) {

}
