package com.trilectus.todolist.service;

import com.trilectus.todolist.modal.TodoItem;
import com.trilectus.todolist.repo.TodoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class service {

    @Autowired
    private TodoRepo repository;

    public List<TodoItem> getAllTodos() {
        return repository.findAll();
    }
    public TodoItem findByTitle(String title) {
        // Implement your logic to find the TodoItem by title
        return repository.findByTitle(title);
    }

    public TodoItem save(TodoItem todoItem) {
        return repository.save(todoItem);
    }

    public TodoItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo item not found with id " + id));
    }

    public TodoItem update(Long id, TodoItem todoItem) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Todo item not found with id " + id);
        }
        // Ensure the ID matches
        todoItem.setId(id); // Assuming you have a setId method in TodoItem
        return repository.save(todoItem);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Todo item not found with id " + id);
        }
        repository.deleteById(id);
    }
}
