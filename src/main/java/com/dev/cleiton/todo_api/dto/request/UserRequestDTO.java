package com.dev.cleiton.todo_api.dto.request;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

    @NotBlank(message = "Nome é obrigatório")
    @Length(min = 4, max = 255, message = "O nome teve ter entre 4 a 255 caracteres")
    String name,

    @NotBlank(message = "Email é obrigatório") 
    @Email(message = "Formato de e-mail é inválido")
    String email,

    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, max = 8, message = "A senha teve ter entre 6 a 8 caracteres")
    String password_hash
) {

}
