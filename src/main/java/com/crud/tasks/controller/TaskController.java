package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @GetMapping
    public List<TaskDto> getTasks(){
        return new ArrayList<TaskDto>();
    }
    @GetMapping
    public TaskDto getTask(Long taskId){
        return new TaskDto(taskId,"test title","test description");
    }
    @DeleteMapping
    public void deleteTask(Long taskId){
        getTasks().remove(taskId);
    }
    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }
    @PostMapping
    public void createTask(TaskDto taskDto) {
    }
}
