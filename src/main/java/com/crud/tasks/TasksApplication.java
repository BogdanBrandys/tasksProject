package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TasksApplication {
	public static void main(String[] args) {
		List<Integer> grades = List.of(3,1,1,5,6,4);
		List<Integer> wages = List.of(4,6,8,4,4,10);
		SpringApplication.run(TasksApplication.class, args);
	}
}
