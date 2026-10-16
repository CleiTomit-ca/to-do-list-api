package com.dev.cleiton.todo_api.Service;

import com.dev.cleiton.todo_api.Exceptions.TaskNotFoundException;
import com.dev.cleiton.todo_api.Models.Task;
import com.dev.cleiton.todo_api.dto.request.TaskRequestDTO;
import com.dev.cleiton.todo_api.dto.response.TaskResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.cleiton.todo_api.Mapper.TaskMapper;
import com.dev.cleiton.todo_api.Repositories.TaskRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private Task findOrFail(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task não encontrada."));
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> listAllTasks(){

        log.info("Listando tarefas encontradas.");

        List<Task> tasks = taskRepository.findAll();

        log.info("Tarefas listadas com sucesso.");

        return taskMapper.toListDTO(tasks);
    }

    @Transactional(readOnly = true)
    public Page<TaskResponseDTO> listAllTaskPageable(Pageable pageable){

        log.info("Listando tarefas encontradas com paginação.");

        Page<Task> tasksPages = taskRepository.findAll(pageable);

        log.info("Tarefas com paginação listada com sucesso.");

        return tasksPages.map(taskMapper::taskResponseDTO);
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO findTaskById(Long id) throws TaskNotFoundException{

        log.info("Buscando tarefa com ID: {}", id);

        Task task = findOrFail(id);

        log.info("Tarefa encontrada com sucesso: {}.", task.getId());

        return taskMapper.taskResponseDTO(task);
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO){

        log.info("Iniciando criação de nova tarefa com título: {}.", taskRequestDTO.title());

        Task task = taskMapper.toEntity(taskRequestDTO);
        Task taskSave = taskRepository.save(task);

        log.info("Tarefa criada com sucesso: {}.", taskSave.getId());

        return taskMapper.taskResponseDTO(taskSave);
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) throws TaskNotFoundException {

        log.info("Buscando tarefa para atualização com ID: {}", id);

        Task task = findOrFail(id);
        taskMapper.updateEntity(taskRequestDTO, task);

        log.info("Tarefa atualizada com sucesso: {}", task.getId());

        return taskMapper.taskResponseDTO(task);
    }

    @Transactional
    public void deleteById(Long id) throws TaskNotFoundException {

        log.info("Buscando tarefa para deletar com ID: {}.", id);

        Task task = findOrFail(id);

        log.info("Tarefa deleta com sucesso: {}.", task.getId());

        taskRepository.delete(task);
    }
}
