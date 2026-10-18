package com.dev.cleiton.todo_api.Repositories;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.cleiton.todo_api.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);
}
