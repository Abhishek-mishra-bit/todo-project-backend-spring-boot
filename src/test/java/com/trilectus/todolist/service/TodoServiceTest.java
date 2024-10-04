package com.trilectus.todolist.service;

import com.trilectus.todolist.modal.TodoItem;
import com.trilectus.todolist.repo.TodoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Mock
    private TodoRepo repository;

    @InjectMocks
    private service todoService;

    private TodoItem todoItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoItem = new TodoItem(); // Create a new instance of TodoItem
        todoItem.setId(1L);
        todoItem.setDescription("Test Todo Item");
    }

    @Test
    void testGetAllTodos() {
        // Add test for getting all todos
        when(repository.findAll()).thenReturn(List.of(todoItem));

        List<TodoItem> todos = todoService.getAllTodos();

        assertEquals(1, todos.size());
        assertEquals(todoItem.getDescription(), todos.get(0).getDescription());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Test save method
        when(repository.save(any(TodoItem.class))).thenReturn(todoItem);

        TodoItem savedTodo = todoService.save(todoItem);

        assertNotNull(savedTodo);
        assertEquals(todoItem.getDescription(), savedTodo.getDescription());
        verify(repository, times(1)).save(todoItem);
    }

    @Test
    void testFindById() {
        // Test find by ID
        when(repository.findById(1L)).thenReturn(Optional.of(todoItem));

        TodoItem foundTodo = todoService.findById(1L);

        assertNotNull(foundTodo);
        assertEquals(todoItem.getId(), foundTodo.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        // Test update method
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any(TodoItem.class))).thenReturn(todoItem);

        TodoItem updatedTodo = todoService.update(1L, todoItem);

        assertNotNull(updatedTodo);
        assertEquals(todoItem.getId(), updatedTodo.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(todoItem);
    }

    @Test
    void testDeleteById() {
        // Test delete method
        when(repository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> todoService.deleteById(1L));
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        // Test delete method when ID is not found
        when(repository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            todoService.deleteById(1L);
        });

        assertEquals("Todo item not found with id 1", exception.getMessage());
    }
}
