package com.dev.cleiton.todo_api.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.dev.cleiton.todo_api.Models.Task;
import com.dev.cleiton.todo_api.dto.request.TaskRequestDTO;
import com.dev.cleiton.todo_api.dto.response.TaskResponseDTO;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", ignore = true)
    Task toEntity(TaskRequestDTO requestDTO);

    TaskResponseDTO taskResponseDTO(Task task);

    List<TaskResponseDTO> toListDTO(List<Task> tasks);

    @Mapping(target = "id", ignore = true)
    void updateEntity(TaskRequestDTO dto, @MappingTarget Task task);
}
