package com.trilectus.todolist.controller;

import com.trilectus.todolist.modal.TodoItem;
import com.trilectus.todolist.service.service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodoController {

    @Autowired
    private service todoService;

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public TodoItem save(@Validated @RequestBody TodoItem todoItem) {
        return todoService.save(todoItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> findById(@PathVariable Long id) {
        TodoItem todoItem = todoService.findById(id);
        return ResponseEntity.ok(todoItem);
    }

    @GetMapping("/todolist/{title}")
    public ResponseEntity<TodoItem> findByTitle(@PathVariable String title) {
        TodoItem todoItem = todoService.findByTitle(title);
        if (todoItem != null) {
            return ResponseEntity.ok(todoItem);
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable Long id, @Validated @RequestBody TodoItem todoItem) {
        TodoItem updatedTodo = todoService.update(id, todoItem);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        todoService.deleteById(id);
        String successfulMsg="item with id: "+ id+"deleted succefully.";
        return ResponseEntity.ok(successfulMsg);
    }
}
