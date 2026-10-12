package com.dev.cleiton.todo_api.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(

    @NotBlank(message = "Titulo é obrigatório")
    @Length(min = 10, max = 255, message = "Titulo deve ter entre 10 a 255 caracteres")
    String title,

    @NotBlank(message = "Descrição é obrigatória")
    @Length(min = 10, max = 255, message = "Descrição deve ter entre 10 a 255 caracteres")
    String description
) {

}
