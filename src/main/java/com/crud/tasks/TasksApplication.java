package com.crud.tasks;

import com.crud.tasks.domain.PrimeNumbers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TasksApplication {
	public static void main(String[] args) {
		PrimeNumbers primeNumbers = new PrimeNumbers();
		int number = 17;
		System.out.println("Number " + number + " is Prime: " + primeNumbers.isPrime(number));
		//SpringApplication.run(TasksApplication.class, args);
	}
}
