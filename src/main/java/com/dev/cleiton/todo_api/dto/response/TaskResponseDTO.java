package com.dev.cleiton.todo_api.dto.response;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    Boolean completed,
    UserResponseDTO user
) {

}
