package com.crud.tasks;

import com.crud.tasks.domain.ChangingCharTask;
import com.crud.tasks.domain.PrimeNumbers;
import com.crud.tasks.domain.SortingTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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
		ChangingCharTask changingCharTask = new ChangingCharTask();
		char test = '&';
		int result = changingCharTask.changeCharToInteger(test);
		System.out.println(result);
		//SpringApplication.run(TasksApplication.class, args);
	}
}
