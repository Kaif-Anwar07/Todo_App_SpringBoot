package com.mka.Todo_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mka.Todo_App.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
