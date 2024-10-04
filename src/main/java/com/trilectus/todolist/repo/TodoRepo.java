package com.trilectus.todolist.repo;

import com.trilectus.todolist.modal.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Long> {
    TodoItem findByTitle(String title);
}
