package com.dev.cleiton.todo_api.Service;

import com.dev.cleiton.todo_api.Exceptions.UserNotFoundException;
import com.dev.cleiton.todo_api.Mapper.UserMapper;
import com.dev.cleiton.todo_api.Models.User;
import com.dev.cleiton.todo_api.Repositories.UserRepository;
import com.dev.cleiton.todo_api.dto.request.UserRequestDTO;
import com.dev.cleiton.todo_api.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findOrFail(Long id) throws UserNotFoundException {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> listAllUsers(){

        log.info("Listando usuários encontrados.");

        List<User> users = userRepository.findAll();

        log.info("Usuários listados com sucesso.");

        return userMapper.toListDTO(users);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id){

        log.info("Buscando usuário com ID: {}.", id);

        User user = findOrFail(id);

        log.info("Usuário encontrado com sucesso ID: {}.", user.getId());

        return userMapper.toResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserByName(String name){

        log.info("Buscando usuário com nome: {}.", name);

        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        log.info("Usuário encontrado com sucesso NOME: {}.", user.getName());

        return userMapper.toResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserByEmail(String email){

        log.info("Buscando usuário com email: {}.", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        log.info("Usuário encontrado com sucesso E-MAIL: {}.", user.getEmail());

        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){

        log.info("Iniciando criação de novo usuário com nome: {}.", userRequestDTO.name());

        User user = userMapper.toEntity(userRequestDTO);

        log.info("Usuário criado com sucesso: {}.", user.getId());

        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO){

        log.info("Buscando usuário para atualização com ID: {}.", id);

        User user = findOrFail(id);

        userMapper.updateEntity(userRequestDTO, user);

        log.info("Usuário atualizado com sucesso: {}.", user.getId());

        return userMapper.toResponseDTO(user);
    }


    public void deleteUserById(Long id){

        log.info("Buscando usuário para deletar com ID: {}.", id);

        User user = findOrFail(id);

        log.info("Usuário encontrado para deletar com sucesso ID: {}.", user.getId());

        userRepository.delete(user);
    }
}
