//package com.trilectus.todolist.controller;
//
//import com.trilectus.todolist.modal.TodoItem;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TodoControllerIntegrationTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testGetAllTodos() {
//        // Send a GET request to the /todos endpoint
//        ResponseEntity<TodoItem[]> response = restTemplate.getForEntity("/todos", TodoItem[].class);
//
//        // Validate the response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0); // Assuming some items exist
//    }
//
//    @Test
//    public void testAddTodo() {
//        // Create a new TodoItem
//        TodoItem newTodo = new TodoItem();
//        newTodo.setDescription("New Integration Test Todo");
//
//        // Send a POST request to the /todos endpoint
//        HttpEntity<TodoItem> request = new HttpEntity<>(newTodo);
//        ResponseEntity<TodoItem> response = restTemplate.postForEntity("/todos", request, TodoItem.class);
//
//        // Validate the response
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(newTodo.getDescription(), response.getBody().getDescription());
//    }
//
//    @Test
//    public void testFindTodoById() {
//        // Assuming you have a todo item with ID 1
//        ResponseEntity<TodoItem> response = restTemplate.getForEntity("/todos/1", TodoItem.class);
//
//        // Validate the response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(1L, response.getBody().getId());
//    }
//
//    @Test
//    public void testUpdateTodo() {
//        // Assuming you have a todo item with ID 1
//        TodoItem updatedTodo = new TodoItem();
//        updatedTodo.setDescription("Updated Todo Description");
//
//        // Send a PUT request to update the todo item
//        HttpEntity<TodoItem> request = new HttpEntity<>(updatedTodo);
//        ResponseEntity<TodoItem> response = restTemplate.exchange("/todos/1", HttpMethod.PUT, request, TodoItem.class);
//
//        // Validate the response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(updatedTodo.getDescription(), response.getBody().getDescription());
//    }
//
//    @Test
//    public void testDeleteTodo() {
//        // Assuming you have a todo item with ID 1
//        ResponseEntity<Void> response = restTemplate.exchange("/todos/1", HttpMethod.DELETE, null, Void.class);
//
//        // Validate the response
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    public void testDeleteTodoNotFound() {
//        // Try deleting a todo item that does not exist
//        ResponseEntity<Void> response = restTemplate.exchange("/todos/999", HttpMethod.DELETE, null, Void.class);
//
//        // Validate the response
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//}
