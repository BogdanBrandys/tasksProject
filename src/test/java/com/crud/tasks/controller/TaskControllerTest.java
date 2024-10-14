package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldReturnTaskList() throws Exception {
        //Given
        List<Task> tasks = Arrays.asList(new Task(1L, "Task1","Content"), new Task(2L, "Task2", "Content"));
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L, "Task1","Content"), new TaskDto(2L, "Task2","Content"));
        //When & Then
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        mvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Task1")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].content", Matchers.is("Content")));
    }

    @Test
    void shouldReturnTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task1","Content");
        TaskDto taskDto = new TaskDto(1L, "Task1","Content");
        //When & Then
        when(dbService.getTask(task.getId())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        mvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Task1")))
                .andExpect(jsonPath("$.content", Matchers.is("Content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task1","Content");
        //When & Then
        when(dbService.deleteTask(task.getId())).thenReturn(true);
        mvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "UpdatedTask","Content");
        TaskDto taskDto = new TaskDto(1L, "UpdatedTask","Content");
        //When & Then
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        System.out.println(taskMapper.mapToTask(taskDto));
        when(dbService.saveTask(task)).thenReturn(task);
        System.out.println(dbService.saveTask(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        System.out.println(taskMapper.mapToTaskDto(task));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mvc.perform(put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task1","Content");
        TaskDto taskDto = new TaskDto(1L, "Task1","Content");
        //When & Then
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mvc.perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}