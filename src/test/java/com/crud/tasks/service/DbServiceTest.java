package com.crud.tasks.service;


import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private DbService taskService;

    @Test
    void shouldReturnTaskWhenTaskExists() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Test Task", "Test Description");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task result = taskService.getTask(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Task", result.getTitle());
    }

    @Test
    void shouldThrowTaskNotFoundExceptionWhenTaskDoesNotExist() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(1L));
    }

    @Test
    void shouldReturnAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L, "Test Task", "Test Description");
        Task task2 = new Task(2L, "Test Task2", "Test Description2");
        tasks.add(task1);
        tasks.add(task2);
        //When
        when(repository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTasks();
        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    void shouldReturnEmptyTasksList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        //When
        when(repository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTasks();
        //Then
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "Test Task", "Test Description");
        //When
        when(repository.save(task)).thenReturn(task);
        Task result = taskService.saveTask(task);
        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Task", result.getTitle());
        assertEquals("Test Description", result.getContent());
    }

    @Test
    void shouldDeleteTask() throws TaskNotFoundException {
        // Given
        Long taskId = 1L;
        when(repository.existsById(taskId)).thenReturn(true);
        // When
        boolean result = taskService.deleteTask(taskId);
        // Then
        assertTrue(result);
        verify(repository, times(1)).deleteById(taskId);
    }
    @Test
    void shouldThrowTaskNotFoundExceptionWhenTaskToDeleteDoesNotExist() throws TaskNotFoundException {
        // Given
        Long taskId = 1L;
        when(repository.existsById(taskId)).thenReturn(false);
        // When
        boolean result = taskService.deleteTask(taskId);
        // Then
        assertFalse(result);
        verify(repository, never()).deleteById(taskId);
    }
}