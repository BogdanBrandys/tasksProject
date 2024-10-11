package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"Test task", "For testing");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(task);
        assertEquals("Test task", task.getTitle());
    }
    @Test
    public void mapToTaskDtoTest(){
        //Given
        Task task = new Task(1L,"Test task", "For testing");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDto);
        assertEquals("For testing", taskDto.getContent());
    }
    @Test
    public void mapToTaskDtoListTest(){
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L,"Test task", "For testing");
        taskList.add(task);
        Task task2 = new Task(2L,"Test task2", "For testing");
        taskList.add(task2);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());
    }

}