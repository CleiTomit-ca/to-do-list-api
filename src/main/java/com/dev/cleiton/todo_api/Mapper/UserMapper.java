package com.dev.cleiton.todo_api.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.dev.cleiton.todo_api.Models.User;
import com.dev.cleiton.todo_api.dto.request.UserRequestDTO;
import com.dev.cleiton.todo_api.dto.response.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "password_hash", ignore = true)
    User toEntity(UserRequestDTO requestDTO);

    UserResponseDTO toResponseDTO(User user);

    List<UserResponseDTO> toListDTO(List<User> user);
}
