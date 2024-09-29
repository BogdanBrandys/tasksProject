package com.crud.tasks;

import com.crud.tasks.domain.ChangingCharTask;
import com.crud.tasks.domain.ChangingCharTaskPro;
import com.crud.tasks.domain.PrimeNumbers;
import com.crud.tasks.domain.SortingTask;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@OpenAPIDefinition
@SpringBootApplication
public class TasksApplication {
	public static void main(String[] args) {
		//PrimeNumbers primeNumbers = new PrimeNumbers();
		//int number = 17;
		//System.out.println("Number " + number + " is Prime: " + primeNumbers.isPrime(number));
		//sorting task 19.1
		//List<Integer> listToSort = List.of(3,1,1,5,6,4);
		//SortingTask sortingTask = new SortingTask();
		//List<Integer> sortedList = sortingTask.sortIntegers(listToSort);
		//System.out.println(sortedList)x;
		//ChangingCharTask changingCharTask = new ChangingCharTask();
		//char test = '&';
		//int result = changingCharTask.changeCharToInteger(test);
		//System.out.println(result);
		ChangingCharTaskPro taskPro = new ChangingCharTaskPro();
		String test = "9.31@234";
		int result = taskPro.changeStringToInteger(test);
		System.out.println(result);
		//SpringApplication.run(TasksApplication.class, args);
	}
}
