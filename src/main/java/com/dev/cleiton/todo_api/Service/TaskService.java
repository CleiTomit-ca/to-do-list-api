package com.dev.cleiton.todo_api.Service;

import com.dev.cleiton.todo_api.Exceptions.TaskNotFoundException;
import com.dev.cleiton.todo_api.Models.Task;
import com.dev.cleiton.todo_api.dto.request.TaskRequestDTO;
import com.dev.cleiton.todo_api.dto.response.TaskResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dev.cleiton.todo_api.Mapper.TaskMapper;
import com.dev.cleiton.todo_api.Repositories.TaskRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponseDTO> listAll(){
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toListDTO(tasks);
    }

    public TaskResponseDTO findTaskById(Long id) throws TaskNotFoundException{
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        return taskMapper.taskResponseDTO(task);
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO){
        Task task = taskMapper.toEntity(taskRequestDTO);
        taskRepository.save(task);

        return taskMapper.taskResponseDTO(task);
    }

    public void deleteById(Long id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}
